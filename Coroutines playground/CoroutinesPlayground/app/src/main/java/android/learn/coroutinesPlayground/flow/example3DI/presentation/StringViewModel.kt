package android.learn.coroutinesPlayground.flow.example3DI.presentation

import android.learn.coroutinesPlayground.flow.example3DI.domain.DeleteStringUseCase
import android.learn.coroutinesPlayground.flow.example3DI.domain.GetCountUseCase
import android.learn.coroutinesPlayground.flow.example3DI.domain.GetStringsUseCase
import android.learn.coroutinesPlayground.flow.example3DI.domain.InsertStringUseCase
import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class StringViewModel @Inject constructor(
    private val deleteStringUseCase: DeleteStringUseCase,
    private val getCountUseCase: GetCountUseCase,
    private val getStringsUseCase: GetStringsUseCase,
    private val insertStringUseCase: InsertStringUseCase
) : ViewModel() {
    suspend fun insertString(str: String) = insertStringUseCase(str)

    suspend fun deleteString(str: String) = deleteStringUseCase(str)

    fun getStrings() = getStringsUseCase()

    fun getCount(str: String) = getCountUseCase(str)

}