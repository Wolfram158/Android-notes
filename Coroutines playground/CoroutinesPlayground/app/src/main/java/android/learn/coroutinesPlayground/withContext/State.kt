package android.learn.coroutinesPlayground.withContext

sealed class State

data class Error(
    val id: Int
) : State()

data object Progress: State()

data class Result(
    val value: String
) : State()