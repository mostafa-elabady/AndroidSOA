package com.pulkit.android.soa

import android.app.Application
import android.content.Context
import android.util.Log
import com.pulkit.feature.registry.Feature
import com.pulkit.feature.registry.IFeatureRegistry
import com.pulkit.feature.registry.features.IAdViewFeature
import com.pulkit.feature.registry.features.IFollowTopicFeature
import com.pulkit.feature.registry.features.IHomeFeature
import javax.inject.Inject

class App: Application() {

    @Inject
    lateinit var featureRegistry: IFeatureRegistry

    override fun onCreate() {
        super.onCreate()
        DaggerBaseComponent.builder().app(this)?.build()?.inject(this)
    }
}

inline fun featureRegistry(context: Context): IFeatureRegistry = (context.applicationContext as App).featureRegistry
