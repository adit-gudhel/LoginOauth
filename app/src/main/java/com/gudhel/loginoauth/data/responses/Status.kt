package com.gudhel.loginoauth.data.responses

import com.google.gson.annotations.SerializedName

data class Status(
    val code: Int,
    val error: Boolean,

    @SerializedName("error_message")
    val errorMessage: List<Any>,

    val message: String
)