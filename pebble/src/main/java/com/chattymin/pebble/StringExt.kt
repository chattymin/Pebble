package com.chattymin.pebble

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.BreakIterator
import java.util.Locale

/**
 * Returns the number of graphemes (user-perceived characters) in the string.
 */
val String.graphemeLength: Int
    get() {
        if (this.isEmpty()) return 0
        if (this.length == 1) return 1
        if (this.isAscii()) return this.length

        val iterator = localBreakIterator
        iterator.setText(this)

        var count = 0
        while (iterator.next() != BreakIterator.DONE) {
            count++
        }
        return count
    }

/**
 * Returns `true` if the string consists only of ASCII characters (0x00 - 0x7F).
 */
private fun String.isAscii(): Boolean {
    for (input in this) {
        if (input.code > ASCII_MAX) return false
    }
    return true
}

/**
 * Returns `true` if this String is a single Emoji character.
 */
fun String.isEmoji(): Boolean {
    return this.graphemeLength == 1 && isEmoji(this.codePointAt(0))
}

/**
 * Returns `true` if this String contains any Emoji characters.
 */
fun String.containsEmoji(): Boolean {
    for (input in this) {
        if (input.isEmoji()) return true
    }
    return false
}

/**
 * Returns string list of emojis from the given text.
 */
fun String.extractEmojis(): List<String> {
    if (this.isBlank()) return emptyList()

    val iterator = BreakIterator.getCharacterInstance(Locale.ROOT)
    iterator.setText(this)

    val emojis = mutableListOf<String>()

    var start = iterator.first()
    var end = iterator.next()

    while (end != BreakIterator.DONE) {
        val grapheme = this.substring(start, end)

        val codePoints = grapheme.codePoints().toArray()
        if (codePoints.any { isEmoji(it) }) {
            emojis.add(grapheme)
        }

        start = end
        end = iterator.next()
    }

    return emojis
}
