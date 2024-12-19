package com.hjw0623.core.presentation.ui

import com.hjw0623.core.domain.util.DataError

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )

        DataError.Network.TIMEOUT -> UiText.StringResource(
            R.string.error_network_timeout
        )

        DataError.Network.CONNECTION_LOST -> UiText.StringResource(
            R.string.error_connection_lost
        )

        DataError.Network.UNAUTHORIZED -> UiText.StringResource(
            R.string.error_unauthorized
        )

        DataError.Network.FORBIDDEN -> UiText.StringResource(
            R.string.error_forbidden
        )

        DataError.Network.NOT_FOUND -> UiText.StringResource(
            R.string.error_not_found
        )

        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.error_too_many_requests
        )

        DataError.Network.UNSUPPORTED_MEDIA_TYPE -> UiText.StringResource(
            R.string.error_unsupported_media_type
        )

        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.error_server_error
        )

        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet
        )

        DataError.Network.SERIALIZATION -> UiText.StringResource(
            R.string.error_serialization
        )
        else -> UiText.StringResource(R.string.error_unknown)
    }
}

