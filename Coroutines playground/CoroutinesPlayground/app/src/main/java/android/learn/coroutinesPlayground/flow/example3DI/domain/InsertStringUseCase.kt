package android.learn.coroutinesPlayground.flow.example3DI.domain

import javax.inject.Inject

class InsertStringUseCase @Inject constructor(
    private val repository: StringRepository
) {
    suspend operator fun invoke(str: String) = repository.insertString(str)
}