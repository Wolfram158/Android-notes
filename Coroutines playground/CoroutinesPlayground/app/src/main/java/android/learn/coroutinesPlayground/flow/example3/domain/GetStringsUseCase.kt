package android.learn.coroutinesPlayground.flow.example3.domain

class GetStringsUseCase(
    private val repository: StringRepository
) {
    operator fun invoke() = repository.getStrings()
}