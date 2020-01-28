package com.example.data.utils

import dagger.Reusable
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface DispatchersProvider {
    val io: CoroutineContext
    val main: CoroutineContext
    val def: CoroutineContext
}

@Reusable
class DispatchersProviderImpl @Inject constructor() : DispatchersProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val def = Dispatchers.Default
}