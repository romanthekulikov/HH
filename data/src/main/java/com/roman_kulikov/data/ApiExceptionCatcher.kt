package com.roman_kulikov.data

import com.roman_kulikov.tools.ExceptionCatcher
import com.roman_kulikov.tools.Result
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

private const val ERROR_NO_INTERNET = "No Internet"
private const val ERROR_UNEXPECTED = "Unexpected error:"
private const val ERROR_SERVER_SHUTDOWN = "Server shutdown"
private const val ERROR_UNIDENTIFIED = "Unidentified error"

class ApiExceptionCatcher @Inject constructor() : ExceptionCatcher {

    override suspend fun <T> launchWithCatch(job: suspend () -> Result<T>): Result<T> {
        return try {
            job()
        } catch (e: UnknownHostException) {
            Result.Failure(cause = ERROR_NO_INTERNET)
        } catch (e: HttpException) {
            Result.Failure(cause = "$ERROR_UNEXPECTED ${e.message()}")
        } catch (e: SocketTimeoutException) {
            Result.Failure(cause = ERROR_SERVER_SHUTDOWN)
        } catch (e: IOException) {
            Result.Failure(cause = ERROR_UNIDENTIFIED)
        }
    }

    override fun <T> withCatch(job: () -> Result<T>): Result<T> {
        return try {
            job()
        } catch (e: UnknownHostException) {
            Result.Failure(cause = ERROR_NO_INTERNET)
        } catch (e: HttpException) {
            Result.Failure(cause = "$ERROR_UNEXPECTED ${e.message()}")
        } catch (e: SocketTimeoutException) {
            Result.Failure(cause = ERROR_SERVER_SHUTDOWN)
        } catch (e: IOException) {
            Result.Failure(cause = ERROR_UNIDENTIFIED)
        }
    }
}