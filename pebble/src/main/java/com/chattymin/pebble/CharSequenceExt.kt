package com.chattymin.pebble

import java.text.BreakIterator

/**
 * Returns the number of graphemes (user-perceived characters) in the character sequence.
 */
val CharSequence.graphemeLength: Int
    get() {
        if (this.isEmpty()) return 0
        if (this.length == 1) return 1
        if (this.isAscii()) return this.length

        val iterator = localBreakIterator
        iterator.setText(this.toString())

        var count = 0
        while (iterator.next() != BreakIterator.DONE) {
            count++
        }
        return count
    }

/**
 * Returns `true` if the char sequence consists only of ASCII characters (0x00 - 0x7F).
 */
private fun CharSequence.isAscii(): Boolean {
    for (input in this) {
        if (input.code > ASCII_MAX) return false
    }
    return true
}

/**
 * Returns `true` if this CharSequence is a single Emoji character.
 */
fun CharSequence.isEmoji(): Boolean {
    return this.graphemeLength == 1 && isEmoji(this.toString().codePointAt(0))
}

/**
 * Returns `true` if this CharSequence contains any Emoji characters.
 */
fun CharSequence.containsEmoji(): Boolean {
    var i = 0
    while (i < this.length) {
        val codePoint = Character.codePointAt(this, i)
        if (isEmoji(codePoint)) return true
        i += Character.charCount(codePoint)
    }
    return false
}
