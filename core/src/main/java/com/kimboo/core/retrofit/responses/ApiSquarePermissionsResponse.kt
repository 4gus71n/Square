package com.kimboo.core.retrofit.responses

import com.google.gson.annotations.SerializedName

data class ApiSquarePermissionsResponse(
    @SerializedName("admin") val admin : Boolean,
    @SerializedName("push") val push : Boolean,
    @SerializedName("pull") val pull : Boolean
)