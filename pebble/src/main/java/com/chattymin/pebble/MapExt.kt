package com.chattymin.pebble

/**
 * Returns `true` if this Map contains any Emoji characters.
 */
fun <K, V> Map<out K, V>.containsEmoji(): Boolean {
    for (entry in this) {
        val key = entry.key
        val value = entry.value

        if (key.containsEmoji()) return true
        if (value.containsEmoji()) return true
    }
    return false
}

/**
 * Returns `true` if this Map contains any Emoji characters in the keys.
 */
fun <K, V> Map<out K, V>.containsEmojiKey(): Boolean {
    for (entry in this) {
        val key = entry.key

        if (key.containsEmoji()) return true
    }
    return false
}

/**
 * Returns `true` if this Map contains any Emoji characters in the values.
 */
fun <K, V> Map<out K, V>.containsEmojiValue(): Boolean {
    for (entry in this) {
        val value = entry.value

        if (value.containsEmoji()) return true
    }
    return false
}
