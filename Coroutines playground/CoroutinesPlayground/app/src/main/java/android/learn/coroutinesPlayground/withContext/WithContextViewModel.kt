package android.learn.coroutinesPlayground.withContext

import android.learn.coroutinesPlayground.R
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class WithContextViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun calculate(strP: String?, strQ: String?, strN: String?) {
        _state.value = Progress
        if (strP.isNullOrBlank() || strQ.isNullOrBlank() || strN.isNullOrBlank()) {
            _state.value = Error(R.string.error_null_or_empty_number)
            return
        }
        viewModelScope.launch {
            try {
                val numberP = strP.toBigInteger()
                val numberQ = strQ.toBigInteger()
                val numberN = strN.toInt()
                assert(numberN >= 0)
                val value = getDesired(numberP, numberQ, numberN)
                _state.value = Result(value)
            } catch (_: NumberFormatException) {
                _state.value = Error(R.string.error_invalid_number)
            } catch (_: AssertionError) {
                _state.value = Error(R.string.error_n)
            }
        }
    }

    private suspend fun getDesired(numberP: BigInteger, numberQ: BigInteger, numberN: Int): String {
        return withContext(Dispatchers.Default) {
            val y = numberP.pow(2).minus(BigInteger.valueOf(4).multiply(numberQ))
            val ab1 = fastPower(BigInteger.ONE, numberP, y, numberN)
            val ab2 = fastPower(BigInteger.ONE.negate(), numberP, y, numberN)
            ab1.first.plus(ab2.first).divide(BigInteger.valueOf(2).pow(numberN)).toString()
        }
    }

    private fun fastPower(s: BigInteger, x: BigInteger, y: BigInteger, n: Int):
            Pair<BigInteger, BigInteger> {
        if (n == 0) {
            return Pair(BigInteger.ONE, BigInteger.ZERO)
        }
        if (n % 2 == 0) {
            val ab = fastPower(s.abs(), x, y, n / 2)
            return Pair(
                ab.first.pow(2) + ab.second.pow(2).multiply(y),
                BigInteger.valueOf(2).multiply(s).multiply(ab.first).multiply(ab.second)
            )
        }
        val ab = fastPower(s, x, y, n - 1)
        return Pair(
            ab.first.multiply(x) + s.multiply(ab.second).multiply(y),
            ab.second.multiply(x) + s.multiply(ab.first)
        )
    }
}