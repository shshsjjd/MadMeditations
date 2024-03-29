package com.coolgirl.madmeditations.Models

data class UserLoginData (
    val login:String,
    val password:String
    )

data class UserLoginDataResponse (
    val id:Int?,
    val email:String?,
    val nickName:String?,
    val avatar:String?,
    val token:String?,
)
