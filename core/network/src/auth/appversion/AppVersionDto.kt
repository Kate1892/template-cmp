package com.eps.pakistan.network.auth.appversion

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AppVersionDto(
    @SerialName("client")
    val client: AppVersionClientDto,
)

@Serializable
class AppVersionClientDto(
    @SerialName("latest")
    val latest: String,
    @SerialName("allowed")
    val allowed: List<String>,
)
