package com.eps.pakistan.network.profile.profile

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ChecksumBody(
    @SerialName("checksum")
    val checksum: String,
) : PostRequestBody
