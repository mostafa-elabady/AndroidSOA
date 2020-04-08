package com.pulkit.home

import android.content.Context
import android.content.Intent
import com.pulkit.feature.registry.Broker
import com.pulkit.feature.registry.features.IHomeFeature
import com.pulkit.home.activity.HomeActivity
import javax.inject.Inject

class HomeFeature
@Inject constructor(private val broker: Broker) : IHomeFeature {
    override fun show(context: Context?) {
        context?.startActivity(Intent(context, HomeActivity::class.java))
    }

    companion object Provider : IHomeFeature.Provider {
        override fun get(broker: Broker): IHomeFeature =
            HomeFeature(broker)
    }
}
