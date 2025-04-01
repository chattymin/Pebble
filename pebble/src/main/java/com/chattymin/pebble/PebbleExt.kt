package com.chattymin.pebble

import java.text.BreakIterator
import java.util.Locale

private val localBreakIterator = ThreadLocal<BreakIterator>().apply {
    set(BreakIterator.getCharacterInstance(Locale.ROOT))
}

// 그래핌 단위로 카운팅된 총 글자수 리턴
fun String.countGraphemes(trim: Boolean = false): Int {
    val target = if (trim) this.trim() else this
    if (target.isEmpty()) return 0
    if (target.length == 1) return 1
    if (target.isLikelyAscii()) return target.length

    // ThreadLocal.get()은 값이 설정되지 않았을 경우 null을 반환할 수 있어서 nullable 타입으로 추론하는듯.
    // 그래서 iterator가 null safety하지 않다고 경고하는디...
    //    val iterator = localBreakIterator.get() ?:
    //    BreakIterator.getCharacterInstance(Locale.ROOT).also {
    //        localBreakIterator.set(it)
    //    }
    // 이렇게 null 처리를 하는건 어떨지???
    val iterator = localBreakIterator.get()
    iterator.setText(target)

    var count = 0
    var boundary = iterator.first()
    while (boundary != BreakIterator.DONE) {
        count++
        boundary = iterator.next()
    }

    return count
}

// 아스키 문자열일 경우 얼리리턴 용도
private fun String.isLikelyAscii(): Boolean {
    var i = 0
    val len = length
    while (i < len) {
        if (this[i].code > 0x7F) return false
        i++
    }
    return true
}

// 그래핌 단위의 문자열 뒤집기(이모지 포함)
fun String.reverseWithGraphemes(): String {
    if (this.isEmpty()) return ""
    if (this.length == 1) return this
    if (this.isLikelyAscii()) return this.reversed()

    val iterator = localBreakIterator.get()
    iterator.setText(this)

    val graphemes = mutableListOf<String>()

    var start = 0
    var end = iterator.next()

    while (end != BreakIterator.DONE) {
        graphemes.add(this.substring(start, end))
        start = end
        end = iterator.next()
    }

    val result = StringBuilder(length)
    for (i in graphemes.size - 1 downTo 0) {
        result.append(graphemes[i])
    }

    return result.toString()
}
