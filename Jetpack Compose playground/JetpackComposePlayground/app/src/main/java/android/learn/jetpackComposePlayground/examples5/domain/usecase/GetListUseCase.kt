package android.learn.jetpackComposePlayground.examples5.domain.usecase

import android.learn.jetpackComposePlayground.examples5.domain.repository.ListRepository
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: ListRepository
) {
    operator fun invoke() = repository.listOfResults
}