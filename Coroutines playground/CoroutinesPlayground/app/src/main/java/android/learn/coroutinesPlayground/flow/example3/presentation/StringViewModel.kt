package android.learn.coroutinesPlayground.flow.example3.presentation

import android.app.Application
import android.learn.coroutinesPlayground.flow.example3.data.repository.StringRepositoryImpl
import android.learn.coroutinesPlayground.flow.example3.domain.DeleteStringUseCase
import android.learn.coroutinesPlayground.flow.example3.domain.GetCountUseCase
import android.learn.coroutinesPlayground.flow.example3.domain.GetStringsUseCase
import android.learn.coroutinesPlayground.flow.example3.domain.InsertStringUseCase
import androidx.lifecycle.AndroidViewModel

class StringViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = StringRepositoryImpl(application)

    private val deleteStringUseCase = DeleteStringUseCase(repository)
    private val getCountUseCase = GetCountUseCase(repository)
    private val getStringsUseCase = GetStringsUseCase(repository)
    private val insertStringUseCase = InsertStringUseCase(repository)

    suspend fun insertString(str: String) = insertStringUseCase(str)

    suspend fun deleteString(str: String) = deleteStringUseCase(str)

    fun getStrings() = getStringsUseCase()

    fun getCount(str: String) = getCountUseCase(str)
}