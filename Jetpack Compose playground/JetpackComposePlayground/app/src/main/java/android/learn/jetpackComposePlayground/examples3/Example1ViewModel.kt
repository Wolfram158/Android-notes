package android.learn.jetpackComposePlayground.examples3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class Example1ViewModel : ViewModel() {
    private val dataSource = mutableListOf<Numbers>().apply {
        repeat(50) {
            val x = Random.nextInt()
            val y = Random.nextInt()
            val z = Random.nextInt()
            add(Numbers(it, x, y, z, x + y + z))
        }
    }

    private val _numbers = MutableLiveData<List<Numbers>>(dataSource)
    val numbers: LiveData<List<Numbers>> = _numbers

    fun addItem(item: Numbers) {
        val newList = _numbers.value?.toMutableList() ?: mutableListOf()
        newList.add(item)
        _numbers.value = newList
    }

    fun removeItem(item: Numbers) {
        val newList = _numbers.value?.toMutableList() ?: mutableListOf()
        newList.remove(item)
        _numbers.value = newList
    }
}