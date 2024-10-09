package com.vanyscore.app.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

enum class Event {
    NOTES_UPDATED
}

object EventBus {
    private val _events = MutableSharedFlow<Event>()
    val eventsSource = _events.asSharedFlow()

    suspend fun triggerNotesUpdated() {
        _events.emit(Event.NOTES_UPDATED)
    }
}