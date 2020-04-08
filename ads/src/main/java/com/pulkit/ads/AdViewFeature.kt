package com.pulkit.ads

import android.content.Context
import android.view.View
import com.pulkit.feature.registry.features.IAdViewFeature

class AdViewFeature
constructor(context: Context, aDependency: String) : IAdViewFeature {
    override fun inflate(context: Context?, placeHolder: View) {

    }


    companion object Provider : IAdViewFeature.Provider {
        override fun get(dependencies: IAdViewFeature.Dependencies): IAdViewFeature {
            return AdViewFeature(dependencies.context, dependencies.string)
        }
    }
}
