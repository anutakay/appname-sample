package com.companyname.appname.domain.common

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class LCE<out R> {

    data class Content<out T>(val data: T) : LCE<T>()
    data class Error(val exception: Exception) : LCE<Nothing>()
    object Loading : LCE<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Content<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [LCE] is of type [Success] & holds non-null [Success.data].
 */
val LCE<*>.succeeded
    get() = this is LCE.Content && data != null
