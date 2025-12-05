package day4

import java.io.File

class Day4 {

    fun part1(file: String): Int {
        val grid = parseInput(file)

        return grid.indices.sumOf { y ->
            grid[y].indices.count { x ->
                grid[y][x] != '.' && countAdjacentPapers(grid, x, y) < 4
            }
        }
    }

    fun part2(file: String): Int {
        var grid = parseInput(file).toMutableList()
        val tmp = grid.toMutableList()

        var total = 0
        while (true) {
            var accessiblePaper = 0
            for (y in 0 until grid.size) {
                for (x in 0 until grid[y].length) {
                    if (grid[y][x] == '.') continue

                    val adjacentPaper = countAdjacentPapers(grid, x, y)
                    if (adjacentPaper < 4) {
                        accessiblePaper++
                        val sb = StringBuilder(tmp[y])
                        sb.setCharAt(x, '.')
                        tmp[y] = sb.toString()
                    }
                }
            }
            if (accessiblePaper == 0) return total
            grid = tmp
            total += accessiblePaper
        }
    }

    fun countAdjacentPapers(grid: List<String>, x: Int, y: Int): Int {
        var paperCount = 0

        for (dy in -1..1) {
            for (dx in -1..1) {
                if (dy == 0 && dx == 0) continue

                val ny = y + dy
                if (ny < 0 || ny >= grid.size) continue

                val nx = x + dx
                if (nx < 0 || nx >= grid[0].length) continue

                if (grid[ny][nx] == '@') paperCount++
            }
        }
        return paperCount
    }

    fun parseInput(file: String): List<String> {
        val filePath = "./src/main/resources/day4/$file"
        return File(filePath).readLines()
    }
}