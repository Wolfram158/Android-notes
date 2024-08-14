package android.learn.coroutinesPlayground.flow.example3DI.domain

import javax.inject.Inject

class GetCountUseCase @Inject constructor(
    private val repository: StringRepository
) {
    operator fun invoke(str: String) = repository.getCount(str)
}