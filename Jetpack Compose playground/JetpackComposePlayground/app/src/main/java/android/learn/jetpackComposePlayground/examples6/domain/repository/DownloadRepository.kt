package android.learn.jetpackComposePlayground.examples6.domain.repository

interface DownloadRepository {
    suspend fun download(link: String): String
}