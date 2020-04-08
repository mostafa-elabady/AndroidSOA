package com.pulkit.feature.registry

import com.pulkit.feature.registry.features.IFollowTopicFeature
import com.pulkit.feature.registry.features.IHomeFeature
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Broker @Inject constructor() {

    enum class State {
        REGISTRY_LOADED, REGISTRY_LOADING
    }

    lateinit var registry: IFeatureRegistry
    val loadingState = BehaviorSubject.createDefault(State.REGISTRY_LOADING)

    internal fun notifyRegistryLoaded(registry: IFeatureRegistry) {
        this.registry = registry
        loadingState.onNext(State.REGISTRY_LOADED)
    }

    fun loadingState(): Maybe<Unit> =
        loadingState.toFlowable(BackpressureStrategy.LATEST).filter { it == State.REGISTRY_LOADED }.map { Unit }.firstElement()
}

inline fun <reified T : Feature> Broker.featureWhenReady() =
    loadingState.toFlowable(BackpressureStrategy.LATEST)
        .filter { it == Broker.State.REGISTRY_LOADED }
        .flatMap { Flowable.fromIterable(registry.getAll()) }
        .ofType(T::class.java)
        .firstElement()
