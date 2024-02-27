package com.choi.part2_ch11

data class Home(
    val user: User,
    val appbarImage: String,
)

data class User(
    val nickname: String,
    val starCount: Int,
    val totalCount: Int,
)