package com.pulkit.feature.registry.features

import android.content.Context
import com.pulkit.feature.registry.Feature
import io.reactivex.Completable
import javax.inject.Inject

interface IFollowTopicFeature : Feature {

    fun followTopic(topic: Topic): Completable

    fun unFollowTopic(topic: Topic): Completable

    data class Dependencies @Inject constructor(val context: Context)

    interface Provider {
        fun get(dependencies: Dependencies): IFollowTopicFeature
    }
}

data class Topic(val id: String)
