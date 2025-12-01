package day1

import java.io.File

class Day1 {

    fun part1(file: String): Int {
        var dialPos = 50
        var zeroCount = 0
        File(file).forEachLine { line ->
            val (dir, mag) = parseLine(line)
            dialPos = when (dir) {
                'R' -> (dialPos + mag) % 100
                'L' -> (dialPos - mag + 100) % 100
                else -> throw IllegalArgumentException("Invalid direction $dir")
            }
            if (dialPos == 0) zeroCount++
        }
        return zeroCount
    }

    fun part2(file: String): Int {
        var dialPos = 50
        var zeroCount = 0

        File(file).forEachLine { line ->
            val (dir, mag) = parseLine(line)
            val turns = mag % 100
            val newDialPos: Int
            zeroCount += mag / 100
            if (dir == 'R') {
                newDialPos = (dialPos + turns) % 100
                if (newDialPos < dialPos) zeroCount++
            } else {
                newDialPos = (dialPos - turns + 100) % 100
                if ((newDialPos > dialPos || newDialPos == 0) && dialPos != 0) zeroCount++
            }
            dialPos = newDialPos
        }
        return zeroCount
    }

    fun parseLine(line: String): Pair<Char, Int> {
        val dir = line[0]
        val mag = line.substring(1)
        return Pair(dir, mag.toInt())
    }
}