package android.learn.jetpackComposePlayground.examples2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Example2ViewModel : ViewModel() {
    private val _clicked = MutableLiveData(0)
    val clicked: LiveData<Int> = _clicked

    fun inc() {
        _clicked.value = _clicked.value?.plus(1)
    }
}