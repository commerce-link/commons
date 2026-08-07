package pl.commercelink.taxonomy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnifiedProductIdentifiersTest {

    @ParameterizedTest
    @CsvSource({
            "'0012345678905', '012345678905'",
            "'0001234567890', '001234567890'",
            "'00000000000001', '000000000001'",
            "'05901234567890', '5901234567890'",
            "'1234567890123', '1234567890123'",
            "'000000000000', '000000000000'",
            "'0000000000', '0000000000'",
            "'0', '0'",
    })
    void stripsAllLeadingZerosDownToTwelveCharFloor(String raw, String expected) {
        assertEquals(expected, UnifiedProductIdentifiers.unifyEan(raw));
    }

    @Test
    void nullEanStaysNull() {
        assertNull(UnifiedProductIdentifiers.unifyEan(null));
    }

    @Test
    void blankEanStaysBlank() {
        assertEquals("", UnifiedProductIdentifiers.unifyEan(""));
    }

    @ParameterizedTest
    @CsvSource({
            "'mfn 1', 'MFN1'",
            "' MFN-1 ', 'MFN-1'",
            "'MFN-1', 'MFN-1'",
    })
    void unifiesMfnByTrimmingSpacesAndUppercasing(String raw, String expected) {
        assertEquals(expected, UnifiedProductIdentifiers.unifyMfn(raw));
    }

    @Test
    void nullMfnStaysNull() {
        assertNull(UnifiedProductIdentifiers.unifyMfn(null));
    }
}
