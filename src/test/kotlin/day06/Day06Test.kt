package day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day06Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 4277556",
        "input.txt, 5227286044585"
    )
    fun test_part1(file: String, expected: Long) {
        val actual = Day06().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 3263827",
        "input.txt, 10227753257799",
    )
    fun test_part2(file: String, expected: Long) {
        val actual = Day06().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
}