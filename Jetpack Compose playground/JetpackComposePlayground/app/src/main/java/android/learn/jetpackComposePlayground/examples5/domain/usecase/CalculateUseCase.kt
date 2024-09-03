package android.learn.jetpackComposePlayground.examples5.domain.usecase

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.domain.repository.AdditionRepository
import javax.inject.Inject

class CalculateUseCase @Inject constructor(
    private val additionRepository: AdditionRepository
) {
    suspend operator fun invoke(quadruple: Quadruple) = additionRepository.calculate(quadruple)
}