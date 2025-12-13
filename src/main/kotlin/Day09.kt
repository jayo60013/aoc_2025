package cyou.umbra

import java.io.File
import kotlin.math.abs

class Day09 {

    data class Point(val x: Int, val y: Int)

    fun part1(file: String): Long {
        val points = parseInput(file)

        return points
            .withIndex()
            .asSequence()
            .flatMap { (i, point) ->
                (i + 1 until points.size).map { j ->
                    getArea(point, points[j])
                }
            }
            .maxOf { it }
    }

    fun part2(file: String): Int {
        error("Not implemented yet")
    }

    fun getArea(a: Point, b: Point): Long {
        val w = abs(a.x - b.x).toLong() + 1
        val h = abs(a.y - b.y).toLong() + 1

        return w * h
    }

    fun parseInput(file: String): List<Point> {
        val fp = "./src/main/resources/day09/$file"
        return File(fp).useLines { lines ->
            lines.map { line ->
                val (x, y) = line.split(",").map { it.toInt() }
                Point(x, y)
            }.toList()
        }
    }

}