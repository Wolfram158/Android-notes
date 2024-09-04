package android.learn.jetpackComposePlayground.examples5.data.repository

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.domain.repository.AdditionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.math.BigInteger
import javax.inject.Inject

class AdditionRepositoryImpl @Inject constructor(): AdditionRepository {
    override fun addToList(quadruple: Quadruple) {
    }

    override suspend fun calculate(quadruple: Quadruple): Quadruple {
        return withContext(Dispatchers.Default) {
            delay(3000)
            val a = quadruple.a.toBigInteger()
            val b = quadruple.b.toBigInteger()
            val mod = quadruple.mod.toBigInteger()
            quadruple.copy(result = fastPower(a, b, mod).toString())
        }
    }

    private fun fastPower(a: BigInteger, b: BigInteger, mod: BigInteger): BigInteger {
        if (b.toInt() == 0) {
            return BigInteger.ONE
        }
        val two = BigInteger.valueOf(2)
        if (b.mod(two) == BigInteger.ZERO) {
            return fastPower(a, b.divide(two), mod).pow(2).mod(mod)
        }
        return a.multiply(fastPower(a, b.dec(), mod)).mod(mod)
    }
}