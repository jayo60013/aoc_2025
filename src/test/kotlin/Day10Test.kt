import cyou.umbra.Day10
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day10Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 7",
        "input.txt, 479"
    )
    fun test_part1(file: String, expected: Int) {
        val actual = Day10().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 0",
        "input.txt, 0",
    )
    fun test_part2(file: String, expected: Int) {
        assertThrows<IllegalStateException> { Day10().part2(file) }
    }
}