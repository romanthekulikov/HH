package com.roman_kulikov.data.catchers

import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteFullException
import android.util.Log
import com.roman_kulikov.tools.ExceptionCatcher
import com.roman_kulikov.tools.Result
import javax.inject.Inject

private const val TAG_DB_LOG = "db_exception"

const val ERROR_TRY_LATE = "Повторите попытку чуть позже"
const val ERROR_MEMORY_LIMIT = "На устройстве не хватает места"
const val ERROR_WRITE = "Произошла ошибка при записи"

class DbExceptionCatcher @Inject constructor() : ExceptionCatcher {
    override suspend fun <T> launchWithCatch(job: suspend () -> Result<T>): Result<T> {
        return try {
            job()
        } catch (e: SQLiteDatabaseLockedException) {
            logException(e)
            Result.Failure(cause = ERROR_TRY_LATE)
        } catch (e: SQLiteFullException) {
            logException(e)
            Result.Failure(cause = ERROR_MEMORY_LIMIT)
        } catch (e: SQLiteDiskIOException) {
            logException(e)
            Result.Failure(cause = ERROR_WRITE)
        }
    }

    override fun <T> withCatch(job: () -> Result<T>): Result<T> {
        return try {
            job()
        } catch (e: SQLiteDatabaseLockedException) {
            logException(e)
            Result.Failure(cause = ERROR_TRY_LATE)
        } catch (e: SQLiteFullException) {
            logException(e)
            Result.Failure(cause = ERROR_MEMORY_LIMIT)
        } catch (e: SQLiteDiskIOException) {
            logException(e)
            Result.Failure(cause = ERROR_WRITE)
        }
    }

    private fun logException(e: Exception) {
        Log.e(TAG_DB_LOG, e.stackTraceToString())
    }
}