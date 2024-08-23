package android.learn.jetpackComposePlayground.examples4

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.geometry.Offset

@Suppress("UNCHECKED_CAST")
data class TableState(
    var leftCorners: List<Offset> = listOf(),
    var centers: List<Offset> = listOf(),
    var isCross: Boolean = false,
    var gameState: GameState = GameState.PLAY,
    var summary: List<List<CellState>> = listOf(begin.toList(), begin.toList(), begin.toList())
) {
    companion object {
        private val begin = listOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY)

        val saver: Saver<MutableState<TableState>, Any> = listSaver(
            save = {
                val table = it.value
                listOf(
                    table.leftCorners.map { it1 -> Pair(it1.x, it1.y) },
                    table.centers.map { it1 -> Pair(it1.x, it1.y) },
                    table.isCross,
                    table.gameState,
                    table.summary
                )
            },
            restore = { it1 ->
                val table = TableState(
                    leftCorners = (it1[0] as List<Pair<Float, Float>>).map { Offset(it.first, it.second) },
                    centers = (it1[1] as List<Pair<Float, Float>>).map { Offset(it.first, it.second) },
                    isCross = it1[2] as Boolean,
                    gameState = it1[3] as GameState,
                    summary = it1[4] as List<List<CellState>>
                )
                mutableStateOf(table)
            }
        )
    }

}