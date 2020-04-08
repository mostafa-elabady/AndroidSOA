package com.pulkit.feature.registry.features

import android.content.Context
import com.pulkit.feature.registry.Feature
import com.pulkit.feature.registry.IFeatureRegistry
import javax.inject.Inject

interface IHomeFeature : Feature {

    fun show(context: Context?)

    interface Provider {
        fun get(): IHomeFeature
    }
}
