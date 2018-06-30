package com.github.stakkato95.myapplication.data.model

import com.squareup.moshi.Json

data class Post(@Json(name = "userId") val userId: String,
                @Json(name = "id") val id: String,
                @Json(name = "title") val title: String,
                @Json(name = "body") val body: String)