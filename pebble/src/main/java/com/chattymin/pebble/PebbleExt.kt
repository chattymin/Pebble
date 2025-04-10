package com.chattymin.pebble

internal const val ASCII_MAX = 0x7F

internal fun isEmoji(codePoint: Int): Boolean {
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
