package com.felix.chucknorrisfact.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class FactResponse(
    @SerializedName("icon_url")
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)