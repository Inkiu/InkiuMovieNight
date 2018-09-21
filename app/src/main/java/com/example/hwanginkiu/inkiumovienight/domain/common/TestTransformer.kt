package com.example.hwanginkiu.inkiumovienight.domain.common

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

class TestTransformer<T>: Transformer<T>() {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
    }

}