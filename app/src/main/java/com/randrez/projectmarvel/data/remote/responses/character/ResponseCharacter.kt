package com.randrez.projectmarvel.data.remote.responses.character

data class ResponseCharacter(
    val code: Int,
    val status: String,
    val data: DataCharactersDTO
)