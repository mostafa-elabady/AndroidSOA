package com.pulkit.followtopic

import com.pulkit.feature.registry.features.IFollowTopicFeature
import com.pulkit.feature.registry.features.Topic
import io.reactivex.Completable
import javax.inject.Inject

class FollowTopicFeature
@Inject constructor(dependencies: IFollowTopicFeature.Dependencies) : IFollowTopicFeature {
    override fun followTopic(topic: Topic): Completable =
        Completable.fromAction { Thread.sleep(1000) }

    override fun unFollowTopic(topic: Topic): Completable =
        Completable.fromAction { Thread.sleep(1000) }

    companion object Provider : IFollowTopicFeature.Provider {
        override fun get(dependencies: IFollowTopicFeature.Dependencies): IFollowTopicFeature =
            FollowTopicFeature(dependencies)
    }
}
