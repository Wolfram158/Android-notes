package android.learn.jetpackComposePlayground.examples5.data.repository

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.domain.repository.AdditionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdditionRepositoryImpl @Inject constructor(): AdditionRepository {
    override fun addToList(quadruple: Quadruple) {
    }

    override suspend fun calculate(quadruple: Quadruple): Quadruple {
        return withContext(Dispatchers.Default) {
            val a = quadruple.a.toBigInteger()
            val b = quadruple.b.toBigInteger()
            val mod = quadruple.mod.toBigInteger()
            quadruple.copy(result = (a.times(b)).mod(mod).toString())
        }
    }
}