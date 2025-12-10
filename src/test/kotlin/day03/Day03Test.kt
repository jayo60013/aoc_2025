package day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day03Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 357",
        "input.txt, 17535"
    )
    fun test_part1(file: String, expected: Long) {
        val actual = Day03().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 3121910778619",
        "input.txt, 173577199527257"
    )
    fun test_part2(file: String, expected: Long) {
        val actual = Day03().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
}