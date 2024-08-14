package android.learn.coroutinesPlayground.flow.example3DI.data.mapper

import android.learn.coroutinesPlayground.flow.example3DI.data.database.StringDbModel
import javax.inject.Inject

class Mapper @Inject constructor() {
    fun mapDbModelToEntity(dbModel: StringDbModel) = dbModel.str

    fun mapEntityToDbModel(str: String) = StringDbModel(str = str)
}