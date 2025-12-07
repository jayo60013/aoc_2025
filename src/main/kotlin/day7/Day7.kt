package day7

import java.io.File

class Day7 {

    data class Point(val x: Int, val y: Int)

    fun part1(file: String): Int {
        val (splitters, start, height) = parseInput(file)
        var beams = mutableSetOf(start)

        var total = 0
        repeat(height) {
            beams = stepBeams(beams).toMutableSet()

            total += beams.count { beam ->
                splitters.contains(beam)
            }

            beams = splitBeams(beams, splitters).toMutableSet()
        }

        return total
    }


    fun part2(file: String): Long {
        val (splitters, start, height) = parseInput(file)
        var beams: Map<Point, Long> = mutableMapOf(start to 1)

        repeat(height) {
            beams = stepAndSplitBeams(beams, splitters)
        }

        return beams.values.sumOf { it.toLong() }
    }

    fun splitBeams(beams: Set<Point>, splitters: Set<Point>): Set<Point> {
        val tmpBeams = mutableSetOf<Point>()

        beams.forEach { beam ->
            if (splitters.contains(beam)) {
                tmpBeams.add(Point(beam.x - 1, beam.y))
                tmpBeams.add(Point(beam.x + 1, beam.y))
            } else {
                tmpBeams.add(beam)
            }
        }
        return tmpBeams
    }

    fun stepBeams(beams: Set<Point>): Set<Point> {
        return beams.map { beam ->
            Point(beam.x, beam.y + 1)
        }.toSet()
    }

    fun stepAndSplitBeams(beams: Map<Point, Long>, splitters: Set<Point>): Map<Point, Long> {
        val splitBeams = mutableMapOf<Point, Long>()
        beams.forEach { (beam, count) ->
            val steppedBeam = Point(beam.x, beam.y + 1)
            if (splitters.contains(steppedBeam)) {
                val l = Point(steppedBeam.x - 1, steppedBeam.y)
                val r = Point(steppedBeam.x + 1, steppedBeam.y)
                splitBeams[l] = splitBeams.getOrDefault(l, 0L) + count
                splitBeams[r] = splitBeams.getOrDefault(r, 0L) + count
            } else {
                splitBeams[steppedBeam] = splitBeams.getOrDefault(steppedBeam, 0L) + count
            }
        }
        return splitBeams
    }

    fun parseInput(file: String): Triple<Set<Point>, Point, Int> {
        val fp = "./src/main/resources/day7/$file"
        val input = File(fp).readLines()

        val splitters = mutableSetOf<Point>()
        var start = Point(0, 0)

        for (y in 0 until input.size) {
            for (x in 0 until input[y].length) {
                if (input[y][x] == 'S') start = Point(x, y)

                if (input[y][x] == '^') splitters.add(Point(x, y))
            }
        }
        return Triple(splitters, start, input.size)
    }
}