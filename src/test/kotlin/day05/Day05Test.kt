package day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day05Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 3",
        "input.txt, 643"
    )
    fun test_part1(file: String, expected: Int) {
        val actual = Day05().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 14",
        "input.txt, 342018167474526",
    )
    fun test_part2(file: String, expected: Long) {
        val actual = Day05().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
}