package day4

import utils.getInputForDay
import java.math.BigInteger

fun main() {
    val input = getInputForDay("day4")
    // val input =  "..@@.@@@@.\n" +
                 "@@@.@.@.@@\n" +
                 "@@@@@.@.@@\n" +
                 "@.@@@@..@.\n" +
                 "@@.@@@@.@@\n" +
                 ".@@@@@@@.@\n" +
                 ".@.@.@.@@@\n" +
                 "@.@@@.@@@@\n" +
                 ".@@@@@@@@.\n" +
                 "@.@.@@@.@."

    val rows = input.lines()
        .filter { it.isNotBlank() }
        .map { it.toCharArray() }


    val resultPartOne = solvePartOne(rows)
    println("Part One: $resultPartOne")

    val resultPartTwo = solvePartTwo(rows)
    println("Part Two: $resultPartTwo")
}

fun solvePartOne(rows: List<CharArray>): Int {
    var accessibleRolls = 0;

    for ((posY, row) in rows.withIndex()) {
        for ((posX, current) in row.withIndex()) {
            if (current != '@') continue
            if (getNumOfNeighbourRolls(rows, posX, posY) < 4) accessibleRolls++
        }
    }
    return accessibleRolls;
}

data class Direction(val dx: Int, val dy: Int)

fun getNumOfNeighbourRolls(rows: List<CharArray>, posX: Int, posY: Int): Int {
    val shifts = listOf(
        Direction(1, 0), Direction(0, 1), Direction(-1, 0), Direction(0, -1),
        Direction(1, -1), Direction(1, 1), Direction(-1, 1), Direction(-1, -1)
    )
    val lenX = rows[0].size
    val lenY = rows.size

    return shifts.count { (dx, dy) ->
        (posX + dx) in 0..<lenX
                && posY + dy in 0..<lenY
                && rows[posY + dy][posX + dx] == '@'
    }
}

fun solvePartTwo(rows: List<CharArray>): Int {
    val workingRows = rows.toMutableList()

    var removedRolls = true;
    var removedRollCount = 0;
    while (removedRolls) {
        removedRolls = false;
        for ((posY, row) in workingRows.withIndex()) {
            for ((posX, current) in row.withIndex()) {
                if (current != '@') continue
                if (getNumOfNeighbourRolls(workingRows, posX, posY) < 4) {
                    workingRows[posY][posX] = '.'
                    removedRolls = true;
                    removedRollCount += 1
                }
            }
        }
    }
    return removedRollCount;
}
