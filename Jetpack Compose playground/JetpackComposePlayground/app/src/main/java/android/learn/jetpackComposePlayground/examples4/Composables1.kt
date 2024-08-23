package android.learn.jetpackComposePlayground.examples4

import android.learn.jetpackComposePlayground.R
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Table() {
    var tableState by rememberSaveable(saver = TableState.saver) {
        mutableStateOf(TableState())
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            when (tableState.gameState) {
                GameState.CROSS -> {
                    Text(stringResource(R.string.cross_won))
                }

                GameState.NULL -> {
                    Text(stringResource(R.string.null_won))
                }

                GameState.DRAW -> {
                    Text(stringResource(R.string.draw))
                }

                GameState.PLAY -> {
                    Text("")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            Modifier
                .background(Color.White)
                .weight(1f)
                .padding(
                    start = 96.dp,
                    top = 288.dp,
                    bottom = 288.dp,
                    end = 96.dp
                )
        ) {
            Canvas(
                modifier = Modifier
                    .height(192.dp)
                    .width(192.dp)
                    .background(Color.Black)
                    .pointerInput(key1 = Unit) {
                        detectTapGestures {
                            tableState = onTapState(tableState, it, size)
                        }
                    }
            ) {
                val path = Path()

                path.lineTo(size.width, 0f)
                path.lineTo(size.width, size.height)
                path.lineTo(0f, size.height)
                path.lineTo(0f, 0f)
                path.moveTo(size.width / 3, 0f)
                path.lineTo(size.width / 3, size.height)
                path.moveTo(2 * size.width / 3, 0f)
                path.lineTo(2 * size.width / 3, size.height)
                path.moveTo(0f, size.height / 3)
                path.lineTo(size.width, size.height / 3)
                path.moveTo(0f, 2 * size.height / 3)
                path.lineTo(size.width, 2 * size.height / 3)

                drawPath(
                    path = path,
                    brush = Brush.linearGradient(listOf(Color.Red, Color.Blue)),
                    style = Stroke(width = 10.dp.toPx())
                )

                val path1 = Path()

                for (corners in tableState.leftCorners) {
                    val x = corners.x
                    val y = corners.y
                    path1.moveTo(x, y)
                    path1.lineTo(x + size.width / 4, y + size.height / 4)
                    path1.moveTo(x + size.width / 4, y)
                    path1.lineTo(x, y + size.height / 4)
                    drawPath(
                        path = path1,
                        color = Color.Yellow,
                        style = Stroke(width = 5.dp.toPx())
                    )
                }

                for (center in tableState.centers) {
                    drawCircle(
                        color = Color.Green,
                        center = center,
                        radius = 20.dp.toPx(),
                        style = Stroke(width = 3.dp.toPx())
                    )
                }

            }

        }
    }
}

fun onTapState(tableState: TableState, it: Offset, size: IntSize): TableState {
    val inX = (3 * it.x / size.width).toInt()
    val inY = (3 * it.y / size.height).toInt()
    val centerX = inX * size.width / 3 + size.width / 6
    val centerY = inY * size.height / 3 + size.height / 6
    val leftCornerX = inX * size.width / 3 + size.width / 24
    val leftCornerY = inY * size.height / 3 + size.height / 24
    val offset1 = Offset(centerX.toFloat(), centerY.toFloat())
    val offset2 = Offset(leftCornerX.toFloat(), leftCornerY.toFloat())
    if (tableState.gameState == GameState.PLAY) {
        if (tableState.isCross && !tableState.centers.contains(offset1)) {
            val newSummary = tableState.summary.toMutableList()
            val newSummaryY = newSummary[inY].toMutableList().apply {
                this[inX] = CellState.CROSS
            }
            newSummary[inY] = newSummaryY
            return tableState.copy(
                leftCorners = tableState.leftCorners + offset2,
                isCross = !tableState.isCross,
                gameState = checkWinner(newSummary),
                summary = newSummary
            )
        } else if (!tableState.isCross && !tableState.leftCorners.contains(offset2)) {
            val newSummary = tableState.summary.toMutableList()
            val newSummaryY = newSummary[inY].toMutableList().apply {
                this[inX] = CellState.NULL
            }
            newSummary[inY] = newSummaryY
            return tableState.copy(
                centers = tableState.centers + offset1,
                isCross = !tableState.isCross,
                gameState = checkWinner(newSummary),
                summary = newSummary
            )
        }
    }
    return tableState
}

fun checkWinner(summary: List<List<CellState>>): GameState {
    if (summary.flatten().count { it != CellState.EMPTY } == 9) {
        return GameState.DRAW
    }
    for (i in 0..2) {
        if (summary[i].all { it == CellState.NULL }) {
            return GameState.NULL
        } else if (summary[i].all { it == CellState.CROSS }) {
            return GameState.CROSS
        }
    }
    val diag1 = listOf(summary[0][0], summary[1][1], summary[2][2])
    var result = check(diag1)
    if (result != GameState.PLAY) {
        return result
    }
    val diag2 = listOf(summary[0][2], summary[1][1], summary[2][0])
    result = check(diag2)
    if (result != GameState.PLAY) {
        return result
    }
    val column1 = listOf(summary[0][0], summary[1][0], summary[2][0])
    result = check(column1)
    if (result != GameState.PLAY) {
        return result
    }
    val column2 = listOf(summary[0][1], summary[1][1], summary[2][1])
    result = check(column2)
    if (result != GameState.PLAY) {
        return result
    }
    val column3 = listOf(summary[0][2], summary[1][2], summary[2][2])
    result = check(column3)
    if (result != GameState.PLAY) {
        return result
    }
    return result
}

fun check(cells: List<CellState>): GameState {
    if (cells.all { it == CellState.NULL }) {
        return GameState.NULL
    } else if (cells.all { it == CellState.CROSS }) {
        return GameState.CROSS
    }
    return GameState.PLAY
}
