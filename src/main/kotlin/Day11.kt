package cyou.umbra

import java.io.File
import java.util.regex.Pattern

class Day10 {

    data class Manual(val lights: List<Boolean>, val buttons: List<List<Int>>, val joltage: List<Int>)

    fun part1(file: String): Int {
        val manuals = parseInput(file)

        return manuals.sumOf { manual ->
            (1..manual.buttons.size).firstOrNull { k ->
                getButtonWiringCombinations(manual.buttons, k)
                    .any { combo ->
                        isButtonPressTurnOffLights(manual.lights, combo)
                    }
            } ?: error("No button combination found. manual=$manual")
        }
    }

    fun part2(file: String): Int {
        error("Need to think on this")
    }

    fun isButtonPressTurnOffLights(lights: List<Boolean>, buttonPresses: List<List<Int>>): Boolean {
        // false -> no change
        // true  -> light is switched
        val parityMap: Map<Int, Boolean> = buttonPresses
            .asSequence()
            .flatten()
            .groupingBy { it }
            .fold(false) { acc, _ -> !acc }

        return lights
            .asSequence()
            .withIndex()
            // If light is on  && light has been switched       -> light is off
            // If light is off && light has not been switched   -> light is off
            // If parityMap does not exist for i, then it has not been switched
            .all { (i, isOn) -> isOn == (parityMap[i] ?: false) }
    }

    fun getButtonWiringCombinations(buttons: List<List<Int>>, k: Int): List<List<List<Int>>> {
        val result = mutableListOf<List<List<Int>>>()

        fun dfs(start: Int, current: MutableList<List<Int>>) {
            if (current.size == k) {
                result += current.toList()
                return
            }

            for (i in start until buttons.size) {
                current += buttons[i]
                dfs(i + 1, current)
                current.removeAt(current.lastIndex)
            }
        }

        dfs(0, mutableListOf())
        return result
    }

    fun parseInput(file: String): List<Manual> {
        val fp = "./src/main/resources/day10/$file"
        val pattern = Pattern.compile(
            """\[(?<lights>[.#]+)\]\s(?<buttons>[\(\)\d+, ]*)\s\{(?<joltage>(\d+,*)+)\}"""
        )
        return File(fp).useLines { lines ->
            lines.map { line ->
                val matcher = pattern.matcher(line)
                if (!matcher.matches()) error("Invalid line: $line")

                val lights = matcher.group("lights")
                val buttons = matcher.group("buttons")
                val joltage = matcher.group("joltage")

                Manual(parseLights(lights), parseButtons(buttons), parseJoltage(joltage))
            }.toList()
        }
    }

    fun parseLights(lights: String): List<Boolean> {
        return lights.map { light ->
            light == '#'
        }.toList()
    }

    fun parseButtons(buttons: String): List<List<Int>> {
        return buttons.split(" ").map { button ->
            val stripped = button.substring(1, button.length - 1)
            stripped.split(",").map { it.toInt() }
        }
    }

    fun parseJoltage(joltage: String): List<Int> {
        return joltage.split(",").map { it.toInt() }
    }
}