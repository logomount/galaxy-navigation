package com.codescape.galaxynavigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

object ExternalUriHandler  {

    private val _uriFlow = Channel<String>(
        capacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val uriFlow: Flow<String> = _uriFlow.receiveAsFlow()

    fun onNewUri(uri: String) {
        _uriFlow.trySend(uri)
    }
}