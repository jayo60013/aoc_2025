package day06

import java.io.File

class Day06 {

    fun part1(file: String): Long {
        val blocks = parseInput(file)

        return blocks.sumOf { block ->
            val lines = block.lines()
            val sign = lines.last()
            val nums = lines.dropLast(1).map { it.trim().toLong() }

            when (sign) {
                "+" -> nums.sum()
                else -> nums.fold(1L) { acc, v -> acc * v }
            }
        }
    }

    fun part2(file: String): Long {
        val blocks = parseInput(file)

        return blocks.sumOf { block ->
            val lines = block.lines()
            val sign = lines.last()
            var acc: Long = if (sign == "+") 0L else 1L
            for (x in lines[0].length - 1 downTo 0) {
                val n = StringBuilder()
                for (y in 0 until lines.size - 1) {
                    n.append(lines[y][x])
                }
                val num = n.toString().trim().toLong()
                acc = when (sign) {
                    "+" -> acc + num
                    else -> acc * num
                }
            }
            acc
        }
    }

    fun parseInput(file: String): List<String> {
        val fp = "./src/main/resources/day06/$file"
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