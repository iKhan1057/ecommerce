package com.ecommerceapp.model.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_profile")
data class Profile(
    @PrimaryKey
    @ColumnInfo(name = "profile_id")
    val id: String = "",
    @ColumnInfo(name = "profile_name")
    var name: String = "",
    @ColumnInfo(name = "profile_gender")
    var gender: String = "",
    @ColumnInfo(name = "profile_email")
    var email: String = "",
    @ColumnInfo(name = "profile_mobile")
    var mobile: String = "",
    @ColumnInfo(name = "profile_image")
    var image: String = "",
    @ColumnInfo(name = "profile_rating")
    var rating: Int = 5,
    @ColumnInfo(name = "profile_banner_color")
    var bannerColor: Long = 0xFF08380B
)