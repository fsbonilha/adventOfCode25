package day2

import utils.getInputForDay
import java.math.BigInteger

fun main () {
    val input = getInputForDay("day2")
    // val input = "11-22,95-115,998-1012, 1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862," +
            "565653-565659,824824821-824824827,2121212118-2121212124"
    val ranges = input.split(",").map { it.trim() }


    val resultPartOne = solvePartOne(ranges)
    println("Part One: $resultPartOne")

    val resultPartTwo = solvePartTwo(ranges)
    println("Part Two: $resultPartTwo")
}

fun solvePartOne(ranges: List<String>): BigInteger {
    var result = BigInteger.ZERO
    for (range in ranges) {
        val split = range.split("-")
        val start = split[0].toLong()
        val end = split[1].toLong()
        for (number in start..end) {
            if (isMirroredNumber(number.toString())) {
                result += number.toBigInteger()
            }
        }
    }
    return result
}

fun isMirroredNumber(number: String): Boolean {
    if (number.length % 2 != 0) return false

    val pivotIndex = number.length / 2

    return number.substring(0, pivotIndex) == number.substring(pivotIndex)
}

fun solvePartTwo(ranges: List<String>): BigInteger {
    var result = BigInteger.ZERO
    for (range in ranges) {
        val split = range.split("-")
        val start = split[0].toLong()
        val end = split[1].toLong()
        for (number in start..end) {
            if (numberHasRepetition(number.toString())) result += number.toBigInteger()
        }
    }
    return result
}

fun numberHasRepetition(number: String): Boolean {
    val length = number.length

    for (divider in 1..<length) {
        if (length % divider != 0) continue

        for (currentIndex in divider..<length step divider) {
            val currentChunk = number.substring(currentIndex, currentIndex + divider)
            val previousChunk = number.substring(currentIndex - divider, currentIndex)
            if (currentChunk != previousChunk) {
                break
            } else if (currentIndex + divider == length) {
                return true
            }
        }
    }

    return false
}
