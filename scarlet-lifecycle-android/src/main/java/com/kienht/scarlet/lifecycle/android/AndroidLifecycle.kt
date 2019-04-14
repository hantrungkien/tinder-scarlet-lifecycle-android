/*
 * © 2018 Match Group, LLC.
 */

package com.kienht.scarlet.lifecycle.android

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.kienht.scarlet.lifecycle.android.core.LifecycleRegistry
import com.tinder.scarlet.Lifecycle

object AndroidLifecycle {
    private const val APPLICATION_THROTTLE_TIMEOUT_MILLIS = 1000L
    private const val ACTIVITY_THROTTLE_TIMEOUT_MILLIS = 500L

    @JvmStatic
    @JvmOverloads
    fun ofApplicationForeground(
        application: Application,
        throttleTimeoutMillis: Long = APPLICATION_THROTTLE_TIMEOUT_MILLIS
    ): Lifecycle {
        return ofLifecycleOwnerForeground(
            application,
            ProcessLifecycleOwner.get(),
            throttleTimeoutMillis
        )
    }

    @JvmStatic
    @JvmOverloads
    fun ofLifecycleOwnerForeground(
        application: Application,
        lifecycleOwner: LifecycleOwner,
        throttleTimeoutMillis: Long = ACTIVITY_THROTTLE_TIMEOUT_MILLIS
    ): Lifecycle {
        return LifecycleOwnerResumedLifecycle(
            lifecycleOwner,
            LifecycleRegistry(throttleTimeoutMillis)
        )
            .combineWith(ConnectivityOnLifecycle(application))
    }

    @JvmStatic
    @JvmOverloads
    fun ofLifecycleServiceStarted(
        application: Application,
        lifecycleOwner: LifecycleOwner,
        throttleTimeoutMillis: Long = ACTIVITY_THROTTLE_TIMEOUT_MILLIS
    ): Lifecycle {
        return LifecycleOwnerStartedLifecycle(
            lifecycleOwner,
            LifecycleRegistry(throttleTimeoutMillis)
        )
            .combineWith(ConnectivityOnLifecycle(application))
    }
}
