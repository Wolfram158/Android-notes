package android.learn.coroutinesPlayground.flow.example1

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> getJob(
    coroutineScope: CoroutineScope,
    flow: Flow<T>,
    ind: Int,
    time: Long
): Job {
    return coroutineScope.launch {
        flow.collect {
            print("[$ind: $it] ")
            delay(time)
        }
    }
}

private suspend fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val flow = flow {
        repeat(10) {
            emit(it)
            delay(400)
        }
    }
    val job1 = getJob(coroutineScope, flow, 1, 500)
    delay(2000)
    val job2 = getJob(coroutineScope, flow, 2, 500)
    job1.join()
    job2.join()
}