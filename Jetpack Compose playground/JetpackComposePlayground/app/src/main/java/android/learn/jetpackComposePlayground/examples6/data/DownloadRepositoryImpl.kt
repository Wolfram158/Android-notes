package android.learn.jetpackComposePlayground.examples6.data

import android.learn.jetpackComposePlayground.examples6.constants.Constants
import android.learn.jetpackComposePlayground.examples6.domain.repository.DownloadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class DownloadRepositoryImpl @Inject constructor(

) : DownloadRepository {
    override suspend fun download(link: String): String {
        return withContext(Dispatchers.IO) {
            delay(5000)
            if (Random.nextDouble(
                    0.0,
                    1.0
                ) < Constants.SUCCESS_PROBABILITY
            ) {
                "Download repository implementation"
            } else {
                throw Exception("Exception occurred")
            }
        }
    }

}