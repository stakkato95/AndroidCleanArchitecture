package com.github.stakkato95.myapplication.data.model

import com.squareup.moshi.Json

data class User(@Json(name = "id") val id: String,
                @Json(name = "name") val name: String,
                @Json(name = "username") val username: String,
                @Json(name = "email") val email: String,
                @Json(name = "phone") val phone: String,
                @Json(name = "website") val website: String)