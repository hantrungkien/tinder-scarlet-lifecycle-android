/*
 * © 2018 Match Group, LLC.
 */

package com.kienht.scarlet.lifecycle.android.core

import com.tinder.scarlet.Lifecycle
import com.tinder.scarlet.LifecycleState
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import java.lang.reflect.Method

internal class FlowableLifecycle(
    private val flowable: Flowable<LifecycleState>
) : Lifecycle, Publisher<LifecycleState> by flowable {

    override fun combineWith(others: List<Lifecycle>): Lifecycle {
        val lifecycles = others + this

        val flowable =
            Flowable.combineLatest(lifecycles) { lifecycle ->
                val state = lifecycle.map { it as LifecycleState }.combine()
                try {
                    lifecycles.unregisterConnectivityBroadcastReceiver(state)
                } catch (e: Exception) {
                }
                state
            }
        return FlowableLifecycle(flowable)
    }

    private fun List<Lifecycle>.unregisterConnectivityBroadcastReceiver(state: LifecycleState) {
        if (state == LifecycleState.Completed) {
            val connectivityClazz: Class<*>? =
                Class.forName("com.tinder.scarlet.lifecycle.android.ConnectivityOnLifecycle")
            connectivityClazz?.let {
                val connectivityOnLifecycle: Lifecycle? = this.find { connectivityClazz.isInstance(it) }
                connectivityOnLifecycle?.let {
                    val obj: Any? = connectivityClazz.cast(connectivityOnLifecycle)
                    val method: Method? = connectivityClazz.getMethod("unregisterReceiver")
                    if (obj != null && method != null) {
                        method.invoke(obj)
                    }
                }
            }
        }
    }
}
