package day1

const val DIAL_INITIAL_POSITION = 50
const val DIAL_TOTAL_SIZE = 100

fun main() {
    val input = utils.getInputForDay("day1")
        .lines()
        .filter { it.isNotBlank() }

    // Sample:
    // val moves = listOf("L68", "L30", "R48", "L5", "R60", "L55", "L1", "L99", "R14", "L82")
    val solutionPartOne = solvePartOne(input)
    println("Part One Password: $solutionPartOne")

    val solutionPartTwo = solvePartTwo(input)
    println("Part Two Password: $solutionPartTwo")
}

fun solvePartOne(moves: List<String>): Int {
    var currentPosition = DIAL_INITIAL_POSITION
    var zeroHitCount = 0

    for (move in moves) {
        val direction = move.take(1)
        var ticks = move.substring(1).toInt()

        if (direction == "L") {
            ticks = 100 - ticks
        }

        currentPosition = (currentPosition + ticks) % DIAL_TOTAL_SIZE

        if (currentPosition == 0) {
            zeroHitCount++
        }
    }

    return zeroHitCount
}

fun solvePartTwo(moves: List<String>): Int {
    var currentPosition = DIAL_INITIAL_POSITION
    var zeroHitCount = 0

    for (move in moves) {
        val direction = move.take(1)
        val ticks = move.substring(1).toInt()
        val remainingTicks = ticks.mod(DIAL_TOTAL_SIZE)
        zeroHitCount += ticks / DIAL_TOTAL_SIZE

        when (direction) {
            "R" -> {
                if (currentPosition + remainingTicks >= DIAL_TOTAL_SIZE) {
                    zeroHitCount++
                }
                currentPosition = (currentPosition + remainingTicks).mod(DIAL_TOTAL_SIZE)
            }
            "L" -> {
                if (currentPosition != 0 && currentPosition - remainingTicks <= 0) {
                    zeroHitCount++
                }
                currentPosition = (currentPosition - remainingTicks).mod(DIAL_TOTAL_SIZE)
            }
        }
    }

    return zeroHitCount
}
