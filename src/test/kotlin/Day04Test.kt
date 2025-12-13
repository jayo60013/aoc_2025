import cyou.umbra.Day04
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day04Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 13",
        "input.txt, 1367"
    )
    fun test_part1(file: String, expected: Int) {
        val actual = Day04().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 43",
        "input.txt, 9144",
    )
    fun test_part2(file: String, expected: Int) {
        val actual = Day04().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
}