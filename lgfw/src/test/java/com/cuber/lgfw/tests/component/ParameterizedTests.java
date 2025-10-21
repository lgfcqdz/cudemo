package com.cuber.lgfw.tests.component;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.failsafe.internal.util.Maths.add;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ParameterizedTests {


    @ParameterizedTest
    @ValueSource(strings = {"valid@email.com", "test.user@domain.com", "user+tag@email.org"})
    void isValidEmail_WithValidEmails_ShouldReturnTrue(String email) {
        assertTrue(EmailValidator.getInstance().isValid(email));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, -5, 5"
    })
    void add_WithVariousInputs_ShouldReturnCorrectResult(int a, int b, int expected) {
        assertEquals(expected, add(a, b));
    }
}
