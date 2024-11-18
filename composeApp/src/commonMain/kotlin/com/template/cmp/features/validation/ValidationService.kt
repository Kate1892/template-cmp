package com.template.cmp.features.validation

internal const val FIELD_GENERAL_MAX_LENGTH = 255
internal const val PHONE_NUMBER_MAX_LENGTH = 16
internal const val STATION_ID_VALID_LENGTH = 15

private const val REGEX_DIGITS_ONLY = "\\d+"
private const val REGEX_DIGITS_AND_PLUS = "[\\d+]*"
private const val REGEX_PHONE_NUMBER = "(?=^.{1,$PHONE_NUMBER_MAX_LENGTH}\$)(^\\+?\\d+$)"

internal class ValidationService {
    internal fun isPhoneValid(str: String): Boolean {
        return REGEX_PHONE_NUMBER.toRegex().matches(str)
    }

    internal fun isPhoneChangeAllowed(str: String): Boolean {
        return REGEX_DIGITS_AND_PLUS.toRegex()
            .matches(str) || str.isEmpty()
    }

    internal fun isOtpChangeAllowed(str: String): Boolean {
        return REGEX_DIGITS_ONLY.toRegex()
            .matches(str) || str.isEmpty()
    }

    internal fun isStationIdValid(str: String): Boolean {
        return REGEX_DIGITS_ONLY.toRegex()
            .matches(str) && str.length == STATION_ID_VALID_LENGTH
    }

    internal fun isStationIdChangeAllowed(str: String): Boolean {
        return REGEX_DIGITS_ONLY.toRegex()
            .matches(str) || str.isEmpty()
    }
}