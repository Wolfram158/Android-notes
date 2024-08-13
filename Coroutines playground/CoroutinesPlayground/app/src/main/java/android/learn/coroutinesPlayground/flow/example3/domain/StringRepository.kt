package android.learn.coroutinesPlayground.flow.example3.domain

import kotlinx.coroutines.flow.Flow

interface StringRepository {
    suspend fun deleteString(str: String)

    suspend fun insertString(str: String)

    fun getCount(str: String): Int

    fun getStrings(): Flow<List<String>>
}