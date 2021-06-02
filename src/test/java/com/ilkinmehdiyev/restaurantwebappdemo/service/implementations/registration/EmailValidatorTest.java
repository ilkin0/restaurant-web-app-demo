package com.ilkinmehdiyev.restaurantwebappdemo.service.implementations.registration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class EmailValidatorTest {

    @Mock
    private static EmailValidator emailValidator;

    private static Pattern pattern;
    private Matcher matcher;
    public static final String EMAIL_VALIDATION_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static String validEmail;
    private static String notValidEmail;

    @BeforeAll
    public static void setUp() {
        emailValidator = new EmailValidator();
        validEmail = "test1email@mail.com";
        validEmail = "notValidEmail";
        pattern = Pattern.compile(EMAIL_VALIDATION_PATTERN);
    }

    @Test
    @DisplayName("test(test1email@mail.com)")
    public void validate_email_when_email_is_valid() {
        when(emailValidator.test(validEmail)).thenReturn(true);

        boolean isValidEmail = emailValidator.test(validEmail);
        assertTrue(isValidEmail);
    }
}