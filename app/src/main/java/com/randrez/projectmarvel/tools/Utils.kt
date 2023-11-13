package com.randrez.projectmarvel.tools

import java.math.BigInteger
import java.security.MessageDigest

object Utils {
    fun calculateMd5(input: String): String =
        BigInteger(1, MessageDigest.getInstance("MD5").digest(input.toByteArray())).toString(16)
            .padStart(32, '0')

}