package day5

import utils.getInputForDay
import java.math.BigInteger

fun main() {
    val input = getInputForDay("day5")
    // val input = "3-5\n10-14\n16-20\n12-18\n\n1\n5\n8\n11\n17\n32"

    val splitInput = input.split("\n\n")
    val ranges = splitInput[0].lines().filter { it.isNotBlank() }
    val ingredients = splitInput[1].lines().filter { it.isNotBlank() }.map { it.toBigInteger() }

    val solutionOne = solvePartOneBinarySearch(ranges, ingredients)
    println("Solution Part One: $solutionOne")

    val solutionTwo = solvePartTwo(ranges)
    println("Solution Part Two: $solutionTwo")
}

fun solvePartOne(ranges: List<String>, ingredients: List<BigInteger>): Int {
    val sortedRanges = ranges.map { range -> range.split("-").map { it.toBigInteger() } }.sortedBy { it[0] }
    val mergedRanges = mergeRanges(sortedRanges)
    var freshCount = 0;

    for (ingredient in ingredients) {
        for (range in mergedRanges) {
            if (ingredient < range[0]) {
                break
            }

            if (ingredient <= range[1]) {
                freshCount++
                break
            }
        }
    }

    return freshCount
}

fun solvePartOneBinarySearch(ranges: List<String>, ingredients: List<BigInteger>): Int {
    val sortedRanges = ranges.map { range -> range.split("-").map { it.toBigInteger() } }.sortedBy { it[0] }
    val mergedRanges = mergeRanges(sortedRanges)

    return ingredients.map { ingredient -> binarySearch(mergedRanges, ingredient) }.count { it }
}

fun solvePartTwo(ranges: List<String>): BigInteger {
    val sortedRanges = ranges.map { range -> range.split("-").map { it.toBigInteger() } }.sortedBy { it[0] }
    val mergedRanges = mergeRanges(sortedRanges)

    return mergedRanges.sumOf { it[1] - it[0] + BigInteger.ONE }
}

fun binarySearch(ranges: List<List<BigInteger>>, target: BigInteger): Boolean {
    var low = 0
    var high = ranges.size - 1

    while (low <= high) {
        val mid = low + (high - low) / 2

        if (ranges[mid][0] <= target && target <= ranges[mid][1]) {
            return true
        } else if (target > ranges[mid][0]) {
            low = mid + 1
        } else {
            high = mid - 1
        }
    }

    return false
}

fun mergeRanges(ranges: List<List<BigInteger>>): List<List<BigInteger>> {
    val mergedRanges = mutableListOf<List<BigInteger>>()
    var rangeStart = ranges[0][0]
    var rangeEnd = ranges[0][1]

    for (i in 1..<ranges.size) {
        if (ranges[i][0] > rangeEnd) {
            mergedRanges.add(listOf(rangeStart, rangeEnd))
            rangeStart = ranges[i][0]
            rangeEnd = ranges[i][1]
        } else if (ranges[i][1] > rangeEnd) {
            rangeEnd = ranges[i][1]
        }
    }

    mergedRanges.add(listOf(rangeStart, rangeEnd))

    return mergedRanges
}