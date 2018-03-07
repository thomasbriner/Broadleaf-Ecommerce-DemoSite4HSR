package ch.hsr.testing.tdd;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RomanNumbersTest {
    @Test
    void convertToRomanNumber() {
        Assertions.assertThat(RomanNumberConverter.convert(1)).isEqualTo("I");
        Assertions.assertThat(RomanNumberConverter.convert(2)).isEqualTo("II");
        Assertions.assertThat(RomanNumberConverter.convert(3)).isEqualTo("III");
        Assertions.assertThat(RomanNumberConverter.convert(4)).isEqualTo("IV");
        Assertions.assertThat(RomanNumberConverter.convert(5)).isEqualTo("V");
        Assertions.assertThat(RomanNumberConverter.convert(6)).isEqualTo("VI");
        Assertions.assertThat(RomanNumberConverter.convert(7)).isEqualTo("VII");
        Assertions.assertThat(RomanNumberConverter.convert(8)).isEqualTo("VIII");
        Assertions.assertThat(RomanNumberConverter.convert(9)).isEqualTo("IX");
        Assertions.assertThat(RomanNumberConverter.convert(10)).isEqualTo("X");
    }
}
