package com.chattymin.pebble

/**
 * Returns `true` if this Array contains any Emoji characters.
 */
fun <T> Array <out T>.containsEmoji(): Boolean {
    for (input in this) {
        if (input.containsEmoji()) return true
    }
    return false
}
