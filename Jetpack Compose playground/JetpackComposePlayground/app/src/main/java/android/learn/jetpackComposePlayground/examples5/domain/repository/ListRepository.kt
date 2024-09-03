package android.learn.jetpackComposePlayground.examples5.domain.repository

import android.learn.jetpackComposePlayground.examples5.domain.entity.Quadruple
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    val listOfResults: Flow<List<Quadruple>>
}