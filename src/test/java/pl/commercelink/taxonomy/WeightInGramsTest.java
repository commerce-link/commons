package pl.commercelink.taxonomy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WeightInGramsTest {

    @ParameterizedTest
    @CsvSource({
            "'1.30',     1300",
            "'1,30',     1300",
            "'0.31',      310",
            "'0,31',      310",
            "'1.30 kg',  1300",
            "'1,30 kg',  1300",
            "'1.30kg',   1300",
            "'1,30kg',   1300",
            "'1.30KG',   1300",
            "' 1.30 kg ', 1300",
            "'500 g',     500",
            "'500g',      500",
            "'500 G',     500",
            "'500G',      500",
            "'0.5 kg',    500"
    })
    void parsesKnownFormatsToGrams(String raw, Integer expectedGrams) {
        assertEquals(expectedGrams, WeightInGrams.parse(raw));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t"})
    void returnsNullForBlankInput(String raw) {
        assertNull(WeightInGrams.parse(raw));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "kg", "g", "-1", "0", "-1.5 kg", "0.0"})
    void returnsNullForUnparseableOrNonphysical(String raw) {
        assertNull(WeightInGrams.parse(raw));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1300", "500", "42"})
    void returnsNullForIntegerWithoutUnit(String raw) {
        assertNull(WeightInGrams.parse(raw));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2000 kg", "1500000 g", "9999.99 kg"})
    void returnsNullForValuesAboveSanityThreshold(String raw) {
        assertNull(WeightInGrams.parse(raw));
    }
}
