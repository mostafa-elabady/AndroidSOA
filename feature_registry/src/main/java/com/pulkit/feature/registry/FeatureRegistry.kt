package com.pulkit.feature.registry

class FeatureRegistry constructor(initFeatures: Set<Feature>, broker: Broker) : IFeatureRegistry {

    private val features = mutableSetOf<Feature>()

    init {
        this.features.addAll(initFeatures.filterNotNull())
        broker.notifyRegistryLoaded(this)
    }

    override fun register(feature: Feature) {
        features.add(feature)
    }

    override fun unregister(feature: Feature) {
        features.remove(feature)
    }

    override fun getAll(): Collection<Feature> =
        features.toSet()

}

inline fun <reified T : Feature> IFeatureRegistry.getOfType(): List<T> {
    return getAll().filterIsInstance<T>()
}
