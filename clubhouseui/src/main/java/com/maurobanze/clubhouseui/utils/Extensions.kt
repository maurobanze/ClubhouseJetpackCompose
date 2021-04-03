package com.maurobanze.clubhouseui.utils
/*
* Utilities for multiplying a list with an integer scalar, resulting
* in a larger list that repeats the original list.
*
*/

operator fun <E> List<E>.times(times: Int): List<E> =
    repeatListInternal(list = this, times)

operator fun <E> Int.times(list: List<E>): List<E> =
    repeatListInternal(list, times = this)

private fun <E> repeatListInternal(
    list: List<E>,
    times: Int
): List<E> {
    require(times > 0) { "Multiplying factor must be 1 or above. It was: $times" }

    if (times == 1 || list.isEmpty()) return list //fast path

    val result = list.toMutableList()
    repeat(times - 1) {
        result += list
    }

    return result
}