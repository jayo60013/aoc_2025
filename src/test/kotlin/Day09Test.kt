import cyou.umbra.Day09
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day09Test {

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 50",
        "input.txt, 4763932976"
    )
    fun test_part1(file: String, expected: Long) {
        val actual = Day09().part1(file)

        assertThat(actual).isEqualTo(expected)
    }
    // 2147471248 to low

    @ParameterizedTest
    @CsvSource(
        "sample.txt, 24",
        "input.txt, 24",
    )
    fun test_part2(file: String, expected: Int) {
        assertThrows<IllegalStateException> { Day09().part2(file) }
    }
}