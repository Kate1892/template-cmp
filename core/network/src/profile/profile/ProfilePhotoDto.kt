package com.eps.pakistan.network.profile.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfilePhotoDto(
    @SerialName("success")
    val success: Boolean,
)
