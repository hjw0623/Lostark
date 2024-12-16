package com.hjw0623.core.presentation.ui

import com.hjw0623.core.domain.util.DataError
import com.hjw0623.core.presentationui.R

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> UiText.StringResource(
            R.string.error_disk_full
        )

        DataError.Network.Timeout -> UiText.StringResource(
            R.string.error_network_timeout
        )

        DataError.Network.ConnectionLost -> UiText.StringResource(
            R.string.error_connection_lost
        )

        DataError.Network.ServerError -> UiText.StringResource(
            R.string.error_server_error
        )

        DataError.Network.Unauthorized -> UiText.StringResource(
            R.string.error_unauthorized
        )

        DataError.Network.Forbidden -> UiText.StringResource(
            R.string.error_forbidden
        )

        DataError.Network.NotFound -> UiText.StringResource(
            R.string.error_not_found
        )

        DataError.Network.TooManyRequests -> UiText.StringResource(
            R.string.error_too_many_requests
        )

        DataError.Network.ServiceUnavailable -> UiText.StringResource(
            R.string.error_service_unavailable
        )

        DataError.Network.GatewayTimeout -> UiText.StringResource(
            R.string.error_gateway_timeout
        )
        else -> UiText.StringResource(R.string.error_unknown)
    }
}

