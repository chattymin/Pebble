package com.chattymin.pebble

import java.text.BreakIterator
import java.util.Locale

private val LocalBreakIterator = ThreadLocal<BreakIterator>().apply {
    set(BreakIterator.getCharacterInstance(Locale.ROOT))
}

internal val localBreakIterator: BreakIterator = LocalBreakIterator.get() ?: initBreakIterator()

private fun initBreakIterator() = BreakIterator.getCharacterInstance(Locale.ROOT).also {
    LocalBreakIterator.set(it)
}
