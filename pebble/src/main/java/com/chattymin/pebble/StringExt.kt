package com.chattymin.pebble

import java.text.BreakIterator

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
    var i = 0

    while (i < this.length) {
        val codePoint = this.codePointAt(i)
        if (isEmoji(codePoint)) return true
        i += Character.charCount(codePoint)
    }

    return false
}

/**
 * Similar to [String.codePoints] which is available from API 24.
 * This internal implementation is provided for compatibility with API 21+.
 */
private fun String.codePointsArray(): IntArray {
    val codePoints = mutableListOf<Int>()

    var i = 0
    while (i < this.length) {
        val codePoint = this.codePointAt(i)
        codePoints.add(codePoint)
        i += Character.charCount(codePoint)
    }

    return codePoints.toIntArray()
}

/**
 * Returns string list of emojis from the given text.
 */
fun String.extractEmojis(): List<String> {
    if (this.isBlank()) return emptyList()

    val iterator = localBreakIterator
    iterator.setText(this)

    val emojis = mutableListOf<String>()

    var start = iterator.first()
    var end = iterator.next()

    while (end != BreakIterator.DONE) {
        val grapheme = this.substring(start, end)

        val codePoints = grapheme.codePointsArray()

        if (codePoints.any { isEmoji(it) }) {
            emojis.add(grapheme)
        }

        start = end
        end = iterator.next()
    }

    return emojis
}

/**
 * Return a new string with all emojis removed.
 */
fun String.filterEmojis(): String {
    if (this.isBlank()) return this

    val iterator = localBreakIterator
    iterator.setText(this)

    val filtered = StringBuilder()

    var start = iterator.first()
    var end = iterator.next()

    while (end != BreakIterator.DONE) {
        val grapheme = this.substring(start, end)

        val codePoints = grapheme.codePointsArray()

        if (!codePoints.any { isEmoji(it) }) {
            filtered.append(grapheme)
        }

        start = end
        end = iterator.next()
    }

    return filtered.toString()
}
