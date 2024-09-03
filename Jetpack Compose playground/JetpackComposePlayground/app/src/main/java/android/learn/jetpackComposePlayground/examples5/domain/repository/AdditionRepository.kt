package android.learn.jetpackComposePlayground.examples5.domain.repository

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple

interface AdditionRepository {
    fun addToList(quadruple: Quadruple)

    suspend fun calculate(quadruple: Quadruple): Quadruple
}