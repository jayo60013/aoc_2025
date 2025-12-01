package day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class Day1Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 3",
        "input.txt, 980"
    )
    fun test_part1(fileName: String, expected: Int) {
        // Given
        val file = "./src/main/resources/day1/$fileName"

        // When
        val actual = Day1().part1(file)

        // Then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 6",
        "input.txt, 5961"
    )
    fun test_part2(fileName: String, expected: Int) {
        // Given
        val file = "./src/main/resources/day1/$fileName"

        // When
        val actual = Day1().part2(file)

        // Then
        assertThat(actual).isEqualTo(expected)
    }
}