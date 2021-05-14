package com.slobodyanyuk_mykhailo.testapp.model.responses

import com.google.gson.annotations.SerializedName

data class LinksResponse (
    @SerializedName("link") val link: String?,
    @SerializedName("home") val home: String?)