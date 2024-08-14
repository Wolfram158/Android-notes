package android.learn.coroutinesPlayground.flow.example3DI.domain

import javax.inject.Inject

class GetStringsUseCase @Inject constructor(
    private val repository: StringRepository
) {
    operator fun invoke() = repository.getStrings()
}