package com.eps.pakistan.network

object ApiRoute {
    private const val URL_PREFIX = "v2/"

    internal const val PRIVACY_POLICY = "${URL_PREFIX}i18n/privacy"
    internal const val TERMS_OF_USE = "${URL_PREFIX}i18n/terms"

    internal const val AUTH = "${URL_PREFIX}authorize/signup"
    internal const val CONFIRM_OTP = "${URL_PREFIX}authorize/confirm"
    internal const val REPEAT_OTP = "${URL_PREFIX}authorize/refreshcode"
    internal const val LOGIN = "${URL_PREFIX}authorize/login"
    internal const val CHECK_PASSWORD = "${URL_PREFIX}authorize/checkpassword"
    internal const val CREATE_PASSWORD = "${URL_PREFIX}authorize/password"
    internal const val CHANGE_PASSWORD = "${URL_PREFIX}authorize/changepassword"
    internal const val STATUS = "${URL_PREFIX}authorize/status"
    internal const val OS_VERSION = "${URL_PREFIX}default/index"

    internal const val TRANSACTION_HISTORY = "${URL_PREFIX}history/list"

    internal const val NOTIFICATIONS = "${URL_PREFIX}notification/list"
    internal const val NOTIFICATIONS_COUNT = "${URL_PREFIX}notification/count"

    internal const val PROVINCES = "${URL_PREFIX}data/countries"
    internal const val CITIES = "${URL_PREFIX}data/cities"

    internal const val PROFILE = "${URL_PREFIX}profile"
    internal const val SAVE_PROFILE = "${URL_PREFIX}profile/save"
    internal const val SET_PROFILE_PHOTO = "${URL_PREFIX}profile/setphoto"
}
