package com.zzz.core.ui.network

import com.zzz.core.ui.domain.network.NetworkObserver
import com.zzz.core.ui.util.ClaveLogger.logD
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkObserverCommon() : NetworkObserver{
    override val isConnected: Flow<Boolean>
        get() = startObservation()

    private fun startObservation()= callbackFlow<Boolean>{

        logD {
            "Building callback flow..."
        }

        val connectivity = Connectivity{
            autoStart = true
        }

        //initial emit
        trySend(true)

        val job = launch {
            connectivity.statusUpdates.collect{status->
                when(status){
                    is Connectivity.Status.Connected -> {
                        trySend(true)
                    }
                    is Connectivity.Status.Disconnected -> {
                        trySend(false)
                    }
                    else -> {}
                }
            }
        }
        awaitClose {
            job.cancel()
            connectivity.stop()

            logD {
                "Cancelling job and stopping connectivity..."
            }
        }
    }.distinctUntilChanged()
    //TODO(Test this ^ for hot emits)

}