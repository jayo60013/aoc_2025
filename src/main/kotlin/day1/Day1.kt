package org.example.day1

import java.io.File

class Day1 {

    val lineRegex = Regex("""([LR])(\d+)""");

    data class Instruction(val dir: String, val turns: Int)

    fun part1(file: String): Int {
        var dialPos = 50;
        var zeroCount = 0;
        File(file).forEachLine { line ->
            lineRegex.find(line)?.destructured?.let { (dir, mag) ->
                dialPos = when (dir) {
                    "R" -> (dialPos + mag.toInt()) % 100
                    "L" -> (dialPos - mag.toInt() + 100) % 100
                    else -> throw IllegalArgumentException("Invalid direction $dir")
                };
                if (dialPos == 0) zeroCount++;
            };
        }
        return zeroCount;
    }

    fun part2(file: String): Int {
        var dialPos = 50;
        var zeroCount = 0;

        File(file).forEachLine { line ->
            lineRegex.find(line)?.destructured?.let { (dir, magStr) ->
                val mag = magStr.toInt();
                val newDialPos: Int;
                zeroCount += mag / 100;
                if (dir == "R") {
                    newDialPos = (dialPos + mag % 100) % 100;
                    if (newDialPos < dialPos) zeroCount++;
                } else {
                    newDialPos = (dialPos - mag % 100 + 100) % 100;
                    if ((newDialPos > dialPos || newDialPos == 0) && dialPos != 0) zeroCount++;
                }
                dialPos = newDialPos;
            }
        }
        return zeroCount;
    }
}