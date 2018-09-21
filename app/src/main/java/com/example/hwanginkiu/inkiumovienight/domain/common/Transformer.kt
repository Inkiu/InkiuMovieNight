package com.example.hwanginkiu.inkiumovienight.domain.common

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>