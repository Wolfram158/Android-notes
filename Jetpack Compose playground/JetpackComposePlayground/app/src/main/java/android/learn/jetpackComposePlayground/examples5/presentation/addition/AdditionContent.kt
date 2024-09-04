package android.learn.jetpackComposePlayground.examples5.presentation.addition

import android.learn.jetpackComposePlayground.R
import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdditionContent(component: AdditionComponent) {
    val state by component.model.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(120.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            InputField(state.quadruple.a, "a", component) {
                state.quadruple.copy(a = it)
            }
            InputField(state.quadruple.b, "b", component) {
                state.quadruple.copy(b = it)
            }
            InputField(state.quadruple.mod, "mod", component) {
                state.quadruple.copy(mod = it)
            }
            OutlinedTextField(
                value = state.quadruple.result,
                onValueChange = {},
                readOnly = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
            Button(
                onClick = { component.onClickCalculate() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.calculate))
            }
            when (state.resultState) {
                is AdditionStore.State.ResultState.Calculated -> {

                }
                AdditionStore.State.ResultState.Calculating -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Black,
                        strokeWidth = 8.dp
                    )
                }
                AdditionStore.State.ResultState.Error -> {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.error_occurred),
                        style = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    )
                }
                AdditionStore.State.ResultState.Initial -> {

                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun InputField(
    value: String,
    hint: String,
    component: AdditionComponent,
    new: (String) -> Quadruple
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            component.changeQuadruple(new(it))
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        placeholder = {
            Text(
                text = hint,
                style = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth()
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )
}