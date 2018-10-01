package com.example.hwanginkiu.inkiumovienight.presentation.common

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val composite = CompositeDisposable()

    override fun onCleared() {
        composite.clear()
    }

    fun Disposable.composite() = composite.add(this)
}