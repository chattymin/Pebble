package com.chattymin.pebble

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
