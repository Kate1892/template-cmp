package com.eps.pakistan.network.profile.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProfileKycStatusDto {
    @SerialName("0")
    NotVerified,

    @SerialName("1")
    InProgress,

    @SerialName("2")
    Verified,

    @SerialName("3")
    Rejected,

    @SerialName("5")
    Changed,
}
