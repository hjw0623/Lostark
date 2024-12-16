package com.hjw0623.core.domain.util

sealed interface DataError: Error {

    enum class Network : DataError {
        Timeout,          // 요청 시간 초과
        ConnectionLost,   // 네트워크 연결 끊김
        ServerError,      // 서버 오류 발생 (5xx 에러)
        Unauthorized,     // 인증 실패 (401 에러)
        Forbidden,        // 접근 금지 (403 에러)
        NotFound,         // 리소스 찾을 수 없음 (404 에러)
        TooManyRequests,  // 요청 한도 초과 (429 에러)
        ServiceUnavailable, // 유지보수 등으로 서비스 불가 (503 에러)
        GatewayTimeout    // 게이트웨이 시간 초과 (504 에러)
    }

    enum class Local: DataError {
        DISK_FULL
    }
}