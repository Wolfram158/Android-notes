package android.learn.coroutinesPlayground.flow.example3DI.data.repository

import android.learn.coroutinesPlayground.flow.example3DI.data.database.StringDao
import android.learn.coroutinesPlayground.flow.example3DI.data.mapper.Mapper
import android.learn.coroutinesPlayground.flow.example3DI.domain.StringRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StringRepositoryImpl @Inject constructor(
    private val stringDao: StringDao,
    private val stringMapper: Mapper
) : StringRepository {
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