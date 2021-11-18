package com.tatatutest.tatatucontacts.app.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tatatutest.tatatucontacts.data.ErrorHandlerImpl
import com.tatatutest.tatatucontacts.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@ExperimentalCoroutinesApi
fun <T> ioFlowWithResult(block: suspend () -> T): Flow<Result<T>> {
    val errorHandler = ErrorHandlerImpl()
    return flow {
        try {
            emit(Result.Success(block.invoke()))
        } catch (e: Exception) {
            emit(Result.Error(errorHandler.getError(e)))
        }
    }
        .flowOn(Dispatchers.IO)
}

@ExperimentalCoroutinesApi
fun <T> ioFlow(block: suspend () -> T): Flow<T> {
    return flow { emit(block.invoke()) }
        .flowOn(Dispatchers.IO)
}

fun Fragment.toast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}
