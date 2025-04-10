package com.chattymin.pebble

/**
 * Returns `true` if this CharArray contains any Emoji characters.
 */
fun CharArray.containsEmoji(): Boolean {
    for (input in this) {
        if (input.isEmoji()) return true
    }
    return false
}
