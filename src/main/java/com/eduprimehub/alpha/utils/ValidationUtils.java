package com.eduprimehub.alpha.utils;

import com.eduprimehub.alpha.models.objects.Displayable;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Slf4j
public final class ValidationUtils {
    public static final String EMAIL_REGEX = "(?i)(?:[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String REGEX_MOBILE_NUMBER = "^[0-9]{6,10}$";
    public static final String REGEX_MOBILE_NUMBER_INDIA = "^[0-9]{10}$";
    public static final String REGEX_MOBILE_NUMBER_OTHERS = "^[0-9]{7,13}$";
    public static final String REGEX_COUNTRY_CODE = "(?!0+$)(^[0-9]{1,4})";
    public static final String REGEX_INTERNAL_FORMAT_NUMBER = "[+][0-9]{9,17}";
    public static final String INDIA_COUNTRY_CODE = "91";

    public static void assertNotNull(Object... args) {
        AtomicInteger index = new AtomicInteger();
        Arrays.stream(args).forEach((arg) -> {
            assertNotNull(arg, String.format("argument at index {%d} must not be null", index.incrementAndGet()));
        });
    }

    public static void assertValid(String arg, String message) throws IllegalArgumentException {
        if (StringUtils.isBlank(arg)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertNotNull(Object arg, String message) throws IllegalArgumentException {
        if (arg == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertValidPinCode(String arg, String msg) throws IllegalArgumentException {
        if (!arg.matches("\\d+")) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void assertNull(Object arg, String message) throws IllegalArgumentException {
        if (arg != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertTrue(Boolean condition, String message) throws IllegalArgumentException {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertValid(Displayable displayable, String message) throws IllegalArgumentException {
        assertNotNull(displayable, message);
        if (StringUtils.isBlank(displayable.getId()) && StringUtils.isBlank(displayable.getText())) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertValidId(Displayable displayable, String message) throws IllegalArgumentException {
        assertNotNull(displayable, message);
        if (StringUtils.isBlank(displayable.getId())) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertValidOptional(Displayable displayable, String message) throws IllegalArgumentException {
        if (displayable != null && StringUtils.isBlank(displayable.getId()) && StringUtils.isBlank(displayable.getText())) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertValidOptional(List<Displayable> arg, String message) throws IllegalArgumentException {
        if (!CollectionUtils.isEmpty(arg)) {
            arg.forEach((a) -> {
                assertValid(a, message);
            });
        }

    }

    public static void assertValidIdOptional(List<Displayable> arg, String message) throws IllegalArgumentException {
        if (!CollectionUtils.isEmpty(arg)) {
            arg.forEach((a) -> {
                assertValidId(a, message);
            });
        }

    }

    public static void assertValidStringOptional(List<String> arg, String message) throws IllegalArgumentException {
        if (!CollectionUtils.isEmpty(arg)) {
            arg.forEach((a) -> {
                assertValid(a, message);
            });
        }

    }

    public static void assertValidEmail(String email, String message) throws IllegalArgumentException {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isValidEmail(String email) {
        return StringUtils.isBlank(email) ? false : email.matches("(?i)(?:[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static void assertValidMobileNumber(String args, String message) throws IllegalArgumentException {
        if (!args.matches("^[0-9]{6,10}$")) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertValidMobileNumberLength(String countryCode, String mobileNumber, String indiaMessage, String othersMessage) throws IllegalArgumentException {
        if ("91".equalsIgnoreCase(countryCode)) {
            if (!mobileNumber.matches("^[0-9]{10}$")) {
                throw new IllegalArgumentException(StringUtils.isBlank(indiaMessage) ? "Please enter a valid mobile number" : indiaMessage);
            }
        } else if (!mobileNumber.matches("^[0-9]{7,13}$")) {
            throw new IllegalArgumentException(StringUtils.isBlank(othersMessage) ? "Mobile number can contain only 7-13 digits" : othersMessage);
        }

    }

    public static void assertValidCountryCode(String args, String message) throws IllegalArgumentException {
        if (!args.matches("(?!0+$)(^[0-9]{1,4})")) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isValidInternationalNumber(String arg) {
        return StringUtils.isBlank(arg) ? false : arg.matches("[+][0-9]{9,17}");
    }

    public static void assertDate(LocalDate date, String message) {
        LocalDate localDate = LocalDate.now();
        if (date != null) {
            assertTrue(!date.isAfter(localDate), message);
        }

    }

    public static void assertDates(LocalDate startDate, LocalDate endDate) {
        assertDate(startDate, "Start date must be before current date");
        assertDate(endDate, "End date must be before current date");
        if (startDate != null && endDate != null) {
            assertTrue(!startDate.isAfter(endDate), "Start date must be before end date");
        }

    }

    public static void assertValidUrl(String url, String message) throws IllegalArgumentException {
        if (!isValidUrl(url)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isValidUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        } else {
            try {
                new URL(url);
                return true;
            } catch (MalformedURLException var2) {
                return false;
            }
        }
    }

    public static void assertMaxLength(String args, int length, String message) {
        assertTrue(StringUtils.isBlank(args) || args.length() <= length, message);
    }

    public static void assertMinLength(String args, int length, String message) {
        assertTrue(StringUtils.isBlank(args) || args.length() >= length, message);
    }

    public static void assertMaxLengthOptional(Displayable displayable, String field) {
        if (displayable != null) {
            assertMaxLength(displayable, field);
        }

    }

    public static void assertMaxLength(Displayable displayable, String field) {
        if (StringUtils.isNotBlank(displayable.getId())) {
            field = field + " uuid should not be greater than 36 character.";
            assertMaxLength(displayable.getId(), 36, field);
        }

        if (StringUtils.isNotBlank(displayable.getText())) {
            field = "You have reached max limit of 250 characters for " + field;
            assertMaxLength(displayable.getText(), 255, field);
        }

    }
}

