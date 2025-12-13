import cyou.umbra.Day02
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day02Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 1227775554",
        "input.txt, 29818212493"
    )
    fun test_part1(file: String, expected: Long) {
        // When
        val actual = Day02().part1(file)

        // Then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 4174379265",
        "input.txt, 37432260594"
    )
    fun test_part2(file: String, expected: Long) {
        // When
        val actual = Day02().part2(file)

        // Then
        assertThat(actual).isEqualTo(expected)
    }
}