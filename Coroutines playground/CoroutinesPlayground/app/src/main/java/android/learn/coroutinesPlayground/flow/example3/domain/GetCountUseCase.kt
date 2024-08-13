package android.learn.coroutinesPlayground.flow.example3.domain

class GetCountUseCase(
    private val repository: StringRepository
) {
    operator fun invoke(str: String) = repository.getCount(str)
}