package android.learn.coroutinesPlayground.flow.example3.domain

class DeleteStringUseCase(
    private val repository: StringRepository
) {
    suspend operator fun invoke(str: String) = repository.deleteString(str)
}