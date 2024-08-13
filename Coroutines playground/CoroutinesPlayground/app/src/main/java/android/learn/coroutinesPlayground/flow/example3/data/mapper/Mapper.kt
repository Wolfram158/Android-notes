package android.learn.coroutinesPlayground.flow.example3.data.mapper

import android.learn.coroutinesPlayground.flow.example3.data.database.StringDbModel

class Mapper {
    fun mapDbModelToEntity(dbModel: StringDbModel) = dbModel.str

    fun mapEntityToDbModel(str: String) = StringDbModel(str = str)
}