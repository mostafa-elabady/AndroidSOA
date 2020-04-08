package com.pulkit.feature.registry.features

import android.content.Context
import android.view.View
import com.pulkit.feature.registry.Feature
import javax.inject.Inject

interface IAdViewFeature : Feature {

    fun inflate(context: Context?, placeHolder: View)

    interface Provider {
        fun get(dependencies: Dependencies): IAdViewFeature
    }

    data class Dependencies @Inject constructor(
        val string: String,
        val context: Context
    )
}
