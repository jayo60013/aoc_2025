import cyou.umbra.Day11
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day11Test {

    @ParameterizedTest
    @CsvSource(
        "p1_sample.txt, 5",
        "input.txt, 479"
    )
    fun test_part1(file: String, expected: Int) {
        val actual = Day11().part1(file)

        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "p2_sample.txt, 2",
        "input.txt, 0",
    )
    fun test_part2(file: String, expected: Int) {
        val actual = Day11().part2(file)

        assertThat(actual).isEqualTo(expected)
    }
    // 1546595184
}