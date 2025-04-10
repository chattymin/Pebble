package com.chattymin.pebble

/**
 * Returns `true` if this Iterable contains any Emoji characters.
 */
fun <T> Iterable<T>.containsEmoji(): Boolean {
    for (input in this) {
        if (input.containsEmoji()) return true
    }
    return false
}
