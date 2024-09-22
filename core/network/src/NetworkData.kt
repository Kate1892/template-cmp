
object NetworkData {

    internal val jsonWithDefaults = Json { encodeDefaults = true }


    internal var isProduction: Boolean = false
        private set

    internal var deviceCode: String = ""
        private set

    internal var deviceName: String = ""
        private set

    internal var deviceType: Int = 0
        private set

    fun initNetwork(
        isProduction: Boolean,
        isLoggingEnabled: Boolean,
        deviceLanguage: String,
    ) {
        this.isProduction = isProduction
        this.isLoggingEnabled = isLoggingEnabled
        this.deviceLanguage = deviceLanguage
        SafeHelper.init(NETWORK_LAYER.substringAfter(":) "))
    }

    fun onLanguageChanged(
        newLanguage: String,
    ) {
        deviceLanguage = newLanguage
    }

    fun initDeviceCode(deviceCode: String) {
        this.deviceCode = deviceCode
    }

    fun initDeviceName(deviceName: String) {
        this.deviceName = deviceName
    }

    fun initDeviceType(deviceType: Int) {
        this.deviceType = deviceType
    }

    fun getIsUserLoggedIn() = NetworkStorage.isUserLoggedIn

    fun getIsUserHasAccessToken() = NetworkStorage.accessToken.isNotEmpty()

    fun getUserPinCode() = NetworkStorage.pincode

    fun clearData() {
        NetworkStorage.clearData()
    }
}
