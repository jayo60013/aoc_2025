package cyou.umbra

import java.io.File
import kotlin.math.max

class Day05 {

    data class Range(val start: Long, val end: Long)

    fun part1(file: String): Int {
        val (ranges, ids) = parseInput(file)

        return ids.count { id ->
            ranges.any { range ->
                range.start <= id && id <= range.end
            }
        }
    }

    fun part2(file: String): Long {
        val (ranges, _) = parseInput(file)
        val sorted = ranges.sortedBy { it.start }
        val consolidated = mutableListOf<Range>()

        var current = sorted.first()
        for (i in 1 until sorted.size) {
            val next = sorted[i]
            val isIntersects = next.start <= current.end

            current = if (isIntersects) {
                Range(current.start, max(current.end, next.end))
            } else {
                consolidated.add(current)
                next
            }
        }
        consolidated.add(current)
        return consolidated.sumOf { it.end - it.start + 1 }
    }

    fun parseInput(file: String): Pair<List<Range>, List<Long>> {
        val filePath = "./src/main/resources/day05/$file"
        val (rangeBlock, idBlock) = File(filePath).readText().split("\n\n")

        val ranges = rangeBlock.split("\n")
            .map { line ->
                val parts = line.split("-")
                Range(parts[0].toLong(), parts[1].toLong())
            }

        val ids = idBlock.split("\n")
            .map { it.toLong() }

        return ranges to ids
    }
}