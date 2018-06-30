package com.github.stakkato95.myapplication.data.model

import com.squareup.moshi.Json

data class User(@Json(name = "id") val id: String,
                @Json(name = "id") val name: String,
                @Json(name = "id") val surname: String,
                @Json(name = "id") val email: String,
                @Json(name = "id") val phone: String,
                @Json(name = "website") val website: String)