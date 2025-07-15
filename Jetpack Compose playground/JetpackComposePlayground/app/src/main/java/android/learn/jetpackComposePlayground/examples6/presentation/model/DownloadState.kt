package android.learn.jetpackComposePlayground.examples6.presentation.model

data class DownloadState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val result: String? = null
)
