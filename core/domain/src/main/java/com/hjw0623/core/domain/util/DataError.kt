package com.hjw0623.core.domain.util

sealed interface DataError: Error {

    enum class Network : DataError {
        TIMEOUT,
        CONNECTION_LOST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        TOO_MANY_REQUESTS,
        UNSUPPORTED_MEDIA_TYPE,
        SERVER_ERROR,
        NO_INTERNET,
        SERIALIZATION,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL
    }
}