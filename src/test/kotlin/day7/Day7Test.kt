package day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day7Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 21",
        "input.txt, 1537"
    )
    fun test_part1(file: String, expected: Int) {
        val actual = Day7().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 40",
        "input.txt, 18818811755665",
    )
    fun test_part2(file: String, expected: Long) {
        val actual = Day7().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
}