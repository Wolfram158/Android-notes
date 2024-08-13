package android.learn.coroutinesPlayground.flow.example3.data.repository

import android.app.Application
import android.learn.coroutinesPlayground.flow.example3.data.database.AppDatabase
import android.learn.coroutinesPlayground.flow.example3.data.mapper.Mapper
import android.learn.coroutinesPlayground.flow.example3.domain.StringRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StringRepositoryImpl(
    application: Application
) : StringRepository {
    private val stringDao = AppDatabase.getInstance(application).getStringDao()
    private val stringMapper = Mapper()

    override suspend fun deleteString(str: String) {
        stringDao.deleteString(str)
    }

    override suspend fun insertString(str: String) {
        stringDao.insertString(stringMapper.mapEntityToDbModel(str))
    }

    override fun getCount(str: String): Int {
        return stringDao.getCount(str)
    }

    override fun getStrings(): Flow<List<String>> { 
        return stringDao.getStrings().map { it1 ->
            it1.map { stringMapper.mapDbModelToEntity(it) }
        }
    }

}