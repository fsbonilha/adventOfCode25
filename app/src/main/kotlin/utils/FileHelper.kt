package utils

fun getInputForDay(day: String): String {
    return object {}.javaClass.classLoader.getResource("$day/input.txt")!!.readText()
}