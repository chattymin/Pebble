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


private const val ASCII_MAX = 0x7F

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
 * Returns `true` if the char sequence consists only of ASCII characters (0x00 - 0x7F).
 */
private fun CharSequence.isAscii(): Boolean {
    for (input in this) {
        if (input.code > ASCII_MAX) return false
    }
    return true
}

/**
 * Returns `true` if this Char is an Emoji.
 */
fun Char.isEmoji(): Boolean {
    return isEmoji(this.code)
}

/**
 * Returns `true` if this String is a single Emoji character.
 */
fun String.isEmoji(): Boolean {
    return this.graphemeLength == 1 && isEmoji(this.codePointAt(0))
}

/**
 * Returns `true` if this CharSequence is a single Emoji character.
 */
fun CharSequence.isEmoji(): Boolean {
    return this.graphemeLength == 1 && isEmoji(this.toString().codePointAt(0))
}

private fun Any?.containsEmoji(): Boolean {
    return when (this) {
        is Char -> this.isEmoji()
        is String -> this.containsEmoji()
        is CharSequence -> this.containsEmoji()
        else -> false
    }
}

private fun isEmoji(codePoint: Int): Boolean {
    return (codePoint in 0x1F600..0x1F64F) ||  // Emoticons (smileys, emotions)
            (codePoint in 0x1F300..0x1F5FF) ||  // Symbols & pictographs (weather, places, objects)
            (codePoint in 0x1F680..0x1F6FF) ||  // Transport & map symbols
            (codePoint in 0x1F700..0x1F77F) ||  // Alchemical symbols
            (codePoint in 0x1F900..0x1F9FF) ||  // Supplemental symbols & pictographs
            (codePoint in 0x1FA70..0x1FAFF) ||  // Symbols & pictographs extended-A
            (codePoint in 0x2600..0x26FF) ||  // Miscellaneous symbols
            (codePoint in 0x2700..0x27BF) ||  // Dingbats
            (codePoint in 0xFE00..0xFE0F) ||  // Variation selectors
            (codePoint == 0x200D) // Zero Width Joiner (ZWJ)
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
 * Returns `true` if this CharSequence contains any Emoji characters.
 */
fun CharSequence.containsEmoji(): Boolean {
    for (input in this) {
        if (input.isEmoji()) return true
    }
    return false
}

/**
 * Returns `true` if this Array contains any Emoji characters.
 */
fun <T> Array <out T>.containsEmoji(): Boolean {
    for (input in this) {
        if (input.containsEmoji()) return true
    }
    return false
}

/**
 * Returns `true` if this CharArray contains any Emoji characters.
 */
fun CharArray.containsEmoji(): Boolean {
    for (input in this) {
        if (input.isEmoji()) return true
    }
    return false
}

/**
 * Returns `true` if this Iterable contains any Emoji characters.
 */
fun <T> Iterable<T>.containsEmoji(): Boolean {
    for (input in this) {
        if (input.containsEmoji()) return true
    }
    return false
}

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
