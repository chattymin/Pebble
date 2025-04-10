package com.chattymin.pebble

internal fun Any?.containsEmoji(): Boolean {
    return when (this) {
        is Char -> this.isEmoji()
        is String -> this.containsEmoji()
        is CharSequence -> this.containsEmoji()
        else -> false
    }
}
