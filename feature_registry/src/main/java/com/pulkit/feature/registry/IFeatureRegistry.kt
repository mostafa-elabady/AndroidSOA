package com.pulkit.feature.registry

interface IFeatureRegistry {

    fun register(feature: Feature)

    fun unregister(feature: Feature)

    fun getAll(): Collection<Feature>
}
