package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormValidatorTest {

    @Test
    void testValidationLogic() {
        // Pusty string
        assertFalse(FormValidator.isValid(""));

        // Wartość null
        assertFalse(FormValidator.isValid(null));

        // Zbyt długi tekst (np. limit 20 znaków)
        assertFalse(FormValidator.isValid("ToJestZdecydowanieZaDlugiTekstDoBazyDanych"));

        // Poprawny tekst
        assertTrue(FormValidator.isValid("JanKowalski"));
    }
}