package day08

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day08Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 10, 40",
        "input.txt, 1000, 96672"
    )
    fun test_part1(file: String, boxesToConnect: Int, expected: Int) {
        val actual = Day08().part1(file, boxesToConnect)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 25272",
        "input.txt, 22517595",
    )
    fun test_part2(file: String, expected: Int) {
        val actual = Day08().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
}