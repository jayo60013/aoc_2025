package cyou.umbra

import java.io.File

class Day07 {

    data class Point(val x: Int, val y: Int)

    fun part1(file: String): Int {
        val (splitters, start, height) = parseInput(file)
        var beams = mutableSetOf(start)

        return (0 until height).sumOf { _ ->
            val count = beams.count { beam ->
                val stepped = Point(beam.x, beam.y + 1)
                splitters.contains(stepped)
            }

            // Split beams
            val tmpBeams = mutableSetOf<Point>()
            beams.forEach { beam ->
                if (splitters.contains(beam)) {
                    tmpBeams.add(Point(beam.x - 1, beam.y + 1))
                    tmpBeams.add(Point(beam.x + 1, beam.y + 1))
                } else {
                    tmpBeams.add(Point(beam.x, beam.y + 1))
                }
            }
            beams = tmpBeams
            count
        }
    }


    fun part2(file: String): Long {
        val (splitters, start, height) = parseInput(file)
        var beams: Map<Point, Long> = mutableMapOf(start to 1)

        repeat(height) {
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
            beams = splitBeams
        }

        return beams.values.sum()
    }

    fun parseInput(file: String): Triple<Set<Point>, Point, Int> {
        val fp = "./src/main/resources/day07/$file"
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