package android.learn.jetpackComposePlayground.examples5.presentation.addition

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import kotlinx.coroutines.flow.StateFlow

interface AdditionComponent {
    val model: StateFlow<AdditionStore.State>

    fun changeQuadruple(quadruple: Quadruple)

    fun onClickAddAndExit()

    fun onClickCalculate()

    fun onClickBack()
}