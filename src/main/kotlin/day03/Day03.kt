package day03

import java.io.File

class Day03 {

    fun part1(file: String): Long {
        return computePart(file, 2)
    }

    fun part2(file: String): Long {
        return computePart(file, 12)
    }

    fun computePart(file: String, n: Int): Long {
        val filePath = "./src/main/resources/day03/$file"
        return File(filePath).useLines { lines ->
            lines.sumOf { batteryPack ->
                getHighestJoltage(batteryPack, n)
            }
        }
    }

    fun getHighestJoltage(batteryPack: String, n: Int): Long {
        val ans = StringBuilder()
        var l = 0
        var r = batteryPack.length - n + 1
        repeat(n) {
            val window = batteryPack.substring(l, r)
            val maxIdx: Int = window.indices.maxBy { window[it].code }
            ans.append(window[maxIdx])
            l += maxIdx + 1
            r++
        }
        return ans.toString().toLong()
    }
}