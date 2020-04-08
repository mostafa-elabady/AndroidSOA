package com.pulkit.home

import android.content.Context
import com.pulkit.feature.registry.features.IHomeFeature
import javax.inject.Inject

class HomeFeature
@Inject constructor() : IHomeFeature {
    override fun show(context: Context?) {
    }

    companion object Provider : IHomeFeature.Provider {
        override fun get(): IHomeFeature =
            HomeFeature()
    }
}
