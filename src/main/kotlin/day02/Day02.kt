package day02

import java.io.File

class Day02 {

    data class Range(val start: Long, val end: Long)

    fun part1(file: String): Long {
        return parseInput(file)
            .asSequence()
            .flatMap { (start, end) -> start..end }
            .filter {
                val str = it.toString()
                if (str.length % 2 != 0) return@filter false
                val mid = str.length / 2
                val left = str.substring(0, mid)
                val right = str.substring(mid)
                left == right
            }
            .sum()
    }

    fun part2(file: String): Long {
        val regex = Regex("^(\\d+)\\1+$")

        return parseInput(file)
            .asSequence()
            .flatMap { (start, end) -> start..end }
            .filter {
                val str = it.toString()
                regex.matches(str)
            }
            .sum()
    }

    fun parseInput(file: String): List<Range> {
        val file = "./src/main/resources/day02/$file"
        val firstLine = File(file).useLines { it.firstOrNull() }
            ?: error("Cannot find $file")

        return firstLine.split(",")
            .map { range ->
                val (start, end) = range.split("-").map { it.toLong() }
                Range(start, end)
            }
    }
}