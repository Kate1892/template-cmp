package com.eps.pakistan.network.profile.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProfileGenderDto {
    @SerialName("0")
    Unspecified,

    @SerialName("1")
    Male,

    @SerialName("2")
    Female,

    @SerialName("3")
    Unknown,
}
