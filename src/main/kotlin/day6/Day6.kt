package day6

import java.io.File

class Day6 {

    //TODO improve this
    fun part1(file: String): Long {
        val (numbers, signs) = parseInput(file)

        var total: Long = 0
        for (x in 0 until numbers[0].size) {
            val sign = signs[x]
            var acc: Long = if (sign == "+") 0 else 1
            for (y in 0 until numbers.size) {
                if (sign == "+") {
                    acc += numbers[y][x]
                } else { // *
                    acc *= numbers[y][x]
                }
            }
            total += acc
        }
        return total
    }

    fun part2(file: String): Long {
        val blocks = parseInput2(file)

        return blocks.sumOf { block ->
            val lines = block.split("\n")
            val sign = lines.last()
            var acc: Long = if (sign == "+") 0L else 1L
            for (x in lines[0].length - 1 downTo 0) {
                val n = StringBuilder()
                for (y in 0 until lines.size - 1) {
                    n.append(lines[y][x])
                }
                if (sign == "+") {
                    acc += n.toString().trim().toLong()
                } else { // *
                    acc *= n.toString().trim().toLong()
                }
            }
            acc
        }
    }

    fun parseInput(file: String): Pair<List<List<Int>>, List<String>> {
        val fp = "./src/main/resources/day6/$file"
        val lines = File(fp).readLines()
        val numberSection = lines.dropLast(1)
        val signLine = lines.last()

        val numbers = numberSection.map { line ->
            line.trim().split(Regex("\\s+")).map { it.toInt() }
        }
        val sign = signLine.trim().split(Regex("\\s+"))

        return numbers to sign
    }

    fun parseInput2(file: String): List<String> {
        val fp = "./src/main/resources/day6/$file"
        val lines = File(fp).readLines()

        var lastBlock = 0
        val blocks = mutableListOf<String>()
        for (i in 0 until lines[0].toCharArray().size) {
            val isBlock = lines.all { line ->
                line[i] == ' '
            }

            if (!isBlock) continue

            val block = lines.joinToString("\n") { line ->
                line.substring(lastBlock, i)
            }.trimEnd()
            blocks.add(block)
            lastBlock = i + 1
        }
        val block = lines.joinToString("\n") { line ->
            line.substring(lastBlock, line.length)
        }.trimEnd()
        blocks.add(block)
        return blocks
    }
}