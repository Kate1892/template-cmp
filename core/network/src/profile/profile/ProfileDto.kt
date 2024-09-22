package com.eps.pakistan.network.profile.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProfileDto(
    @SerialName("personal")
    val personal: ProfilePersonalInfoDto,
    @SerialName("address")
    val address: ProfileAddressInfoDto,
    @SerialName("id_card")
    val idCard: ProfileIdCardDto,
    @SerialName("is_verified")
    val kycStatus: ProfileKycStatusDto,
    @SerialName("phone")
    val phone: String,
    @SerialName("photo")
    val photo: String?,
)

@Serializable
class ProfilePersonalInfoDto(
    @SerialName("name")
    val name: String,
    @SerialName("middlename")
    val middleName: String,
    @SerialName("lastname")
    val lastName: String,
    @SerialName("gender")
    val gender: ProfileGenderDto,
    @SerialName("birthdate")
    val birthdate: String,
    @SerialName("email")
    val email: String,
    @SerialName("occupation")
    val occupation: String,
)

@Serializable
class ProfileAddressInfoDto(
    @SerialName("country_id")
    val countryId: String,
    @SerialName("city_id")
    val cityId: String,
    @SerialName("address_1")
    val address1: String,
    @SerialName("address_2")
    val address2: String,
)

@Serializable
class ProfileIdCardDto(
    @SerialName("id_number")
    val idNumber: String,
    @SerialName("issue_date")
    val issueDate: String,
    @SerialName("expiry_date")
    val expiryDate: String,
)
