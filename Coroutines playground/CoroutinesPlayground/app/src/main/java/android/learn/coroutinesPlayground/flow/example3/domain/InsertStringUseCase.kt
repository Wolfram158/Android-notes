package android.learn.coroutinesPlayground.flow.example3.domain

class InsertStringUseCase(
    private val repository: StringRepository
) {
    suspend operator fun invoke(str: String) = repository.insertString(str)
}