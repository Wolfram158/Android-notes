package android.learn.jetpackComposePlayground.examples6.presentation

import android.learn.jetpackComposePlayground.examples6.domain.repository.DownloadRepository
import android.learn.jetpackComposePlayground.examples6.presentation.model.Action
import android.learn.jetpackComposePlayground.examples6.presentation.model.DownloadState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val downloadRepository: DownloadRepository
) : ViewModel() {
    private val _state = MutableStateFlow<DownloadState>(DownloadState())
    val state = _state.asStateFlow()

    private fun reduceDownload() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = downloadRepository.download("")
                _state.value = _state.value.copy(isLoading = false, result = result, error = null)
            } catch (e: Exception) {
                _state.value =
                    _state.value.copy(isLoading = false, result = null, error = e.message)
            }
        }
    }

    fun action(action: Action) {
        when (action) {
            Action.Download -> {
                reduceDownload()
            }
        }
    }
}