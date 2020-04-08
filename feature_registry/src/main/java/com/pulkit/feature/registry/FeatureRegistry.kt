package com.pulkit.feature.registry

class FeatureRegistry constructor(initFeatures: Set<Feature>) : IFeatureRegistry {

    private val features = mutableSetOf<Feature>()

    init {
        this.features.addAll(initFeatures.filterNotNull())
    }

    override fun register(feature: Feature) {
        features.add(feature)
    }

    override fun unregister(feature: Feature) {
        features.remove(feature)
    }

    override fun getAll(): Collection<Feature> =
        features.toSet()

    override fun getOfType(type: Class<out Feature>): List<Feature> =
        features.filter { it.javaClass == type }
}
