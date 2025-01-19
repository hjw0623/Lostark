package com.hjw0623.core.data.util

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeURLPath(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}