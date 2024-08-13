package android.learn.coroutinesPlayground.flow.example2

import android.learn.coroutinesPlayground.flow.example1.getJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private val delimiter = "_".repeat(120)

private fun delimit() {
    println()
    println(delimiter)
}

private suspend fun example21() {
    println("Example 21")
    val flow = listOf(1, 5, 10, 15, 20, 25).asFlow()
    flow.collect {
        print("$it ")
    }
    delimit()
}

private suspend fun example22() {
    println("Example 22")
    (1..40).asFlow()
        .filter { it % 7 == 3 }
        .map { it * 10 }
        .collect {
            print("$it ")
        }
    delimit()
}

private suspend fun example23(
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    part: Int
) {
    println("Example 23.$part")
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val flow = MutableSharedFlow<Int>(replay, extraBufferCapacity, onBufferOverflow)
    coroutineScope.launch {
        repeat(10) {
            flow.emit(it)
            delay(100)
        }
    }
    delay(200)
    getJob(coroutineScope, flow, 1, 20)
    delay(500)
    getJob(coroutineScope, flow, 2, 50)
    delay(500)
    coroutineScope.cancel()
    delimit()
}

private suspend fun main() {
    example21()
    example22()
    example23(part = 1)
    example23(replay = 1, part = 2)
    example23(replay = 2, part = 3)
    example23(extraBufferCapacity = 1, part = 4)
    example23(extraBufferCapacity = 2, part = 5)
    example23(extraBufferCapacity = 3, part = 6)
    example23(replay = 1, extraBufferCapacity = 2, part = 7)
    example23(replay = 2, extraBufferCapacity = 2, part = 8)
    example23(replay = 2, extraBufferCapacity = 1, part = 9)
    example23(
        replay = 1,
        extraBufferCapacity = 2,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
        part = 10
    )
    example23(
        replay = 2,
        extraBufferCapacity = 2,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
        part = 11
    )
    example23(
        replay = 2,
        extraBufferCapacity = 2,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        part = 12
    )
    example23(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
        part = 13
    )
    example23(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        part = 14
    )
}
