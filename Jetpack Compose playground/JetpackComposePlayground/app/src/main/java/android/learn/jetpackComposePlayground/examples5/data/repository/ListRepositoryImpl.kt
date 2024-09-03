package android.learn.jetpackComposePlayground.examples5.data.repository

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import android.learn.jetpackComposePlayground.examples5.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(): ListRepository {
    override val listOfResults: Flow<List<Quadruple>> = flow {
        listOf<Quadruple>()
    }
}