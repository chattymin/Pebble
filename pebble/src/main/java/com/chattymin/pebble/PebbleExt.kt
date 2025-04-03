package com.chattymin.pebble

import java.text.BreakIterator
import java.util.Locale

private val LocalBreakIterator = ThreadLocal<BreakIterator>().apply {
    set(BreakIterator.getCharacterInstance(Locale.ROOT))
}

private val localBreakIterator: BreakIterator = LocalBreakIterator.get() ?: initBreakIterator()

private fun initBreakIterator() = BreakIterator.getCharacterInstance(Locale.ROOT).also {
    LocalBreakIterator.set(it)
}

private const val ASCII_MAX = 0x7F

/**
 * Returns the number of graphemes (user-perceived characters) in the string.
 * Unlike the simple `length` property, which counts code units, this function
 * correctly handles grapheme clusters, including emojis and combined characters.
 * It uses `BreakIterator` to determine boundaries, ensuring accuracy for complex scripts.
 */
fun String.graphemeLength(): Int {
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
 * Checks if the string consists only of ASCII characters (0x00 - 0x7F).
 * This function is useful for performance optimizations, as ASCII-only
 * strings do not require complex Unicode processing.
 * If any character falls outside the ASCII range, it immediately returns `false`.
 */
private fun String.isAscii(): Boolean {
    for (input in this) {
        if (input.code > ASCII_MAX) return false
    }
    return true
}
