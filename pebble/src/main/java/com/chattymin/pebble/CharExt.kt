package com.chattymin.pebble

/**
 * Returns `true` if this Char is an Emoji.
 */
fun Char.isEmoji(): Boolean {
    return isEmoji(this.code)
}

