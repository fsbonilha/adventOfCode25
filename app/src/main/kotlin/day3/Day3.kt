package day3

import utils.getInputForDay
import java.math.BigInteger
import kotlin.plus

fun main() {
    // val input = "987654321111111\n811111111111119\n234234234234278\n818181911112111"
    val input = getInputForDay("day3")

    val parsedInput = input.lines().filter { it.isNotBlank() }

    val resultPartOne = solvePartOne(parsedInput)
    println("Part One: $resultPartOne")

    val resultPartTwo = solvePartTwo(parsedInput, 12)
    println("Part Two: $resultPartTwo")
}

fun solvePartOne(banks: List<String>): Int {
    var result = 0
    for (bank in banks) {
        var decimalDigit = 0
        var unitDigit = 0
        for ((index, battery) in bank.withIndex()) {
            val batteryVoltage = battery.toString().toInt()
            if (batteryVoltage > decimalDigit && index < bank.lastIndex) {
                decimalDigit = batteryVoltage
                unitDigit = 0
            } else if (batteryVoltage > unitDigit) {
                unitDigit = batteryVoltage
            }
        }
        result += (decimalDigit * 10 + unitDigit)
    }
    return result
}

fun solvePartTwo(banks: List<String>, numbersToGet: Int): BigInteger {
    return banks.fold(BigInteger.ZERO) { acc, bank ->
        acc + getLargestNumber(bank, numbersToGet)
    }
}

fun getLargestNumber(bank: String, numbersToGet: Int): BigInteger {
    var startFrom = 0
    var result = BigInteger.ZERO
    for (remaining in numbersToGet - 1 downTo 0) {
        var maxVoltage = 0
        for (index in startFrom..bank.lastIndex - remaining) {
            val voltage = bank[index].toString().toInt()
            if (voltage > maxVoltage) {
                maxVoltage = voltage
                startFrom = index + 1
            }
        }
        result += maxVoltage.toBigInteger() * BigInteger.valueOf(10).pow(remaining)
    }
    return result
}
