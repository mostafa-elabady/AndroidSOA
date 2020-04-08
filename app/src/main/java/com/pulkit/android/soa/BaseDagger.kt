package com.pulkit.android.soa

import android.app.Application
import android.content.Context
import com.pulkit.feature.registry.Broker
import com.pulkit.feature.registry.Feature
import com.pulkit.feature.registry.FeatureRegistry
import com.pulkit.feature.registry.IFeatureRegistry
import com.pulkit.feature.registry.features.IAdViewFeature
import com.pulkit.feature.registry.features.IFollowTopicFeature
import com.pulkit.feature.registry.features.IHomeFeature
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.lang.Exception
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder?

        fun build(): BaseComponent?
    }
}

const val AD_IMPL_PROVIDER_CLASS = "com.pulkit.ads.AdViewFeature\$Provider"
const val HOME_IMPL_PROVIDER_CLASS = "com.pulkit.home.HomeFeature\$Provider"
const val FOLLOW_TOPIC_IMPL_PROVIDER_CLASS = "com.pulkit.followtopic.FollowTopicFeature\$Provider"

@Module
object BaseModule {

    @Provides
    @IntoSet
    @Singleton
    fun adFeature(adDependencies: IAdViewFeature.Dependencies): Feature {
        val provider =
            Class.forName(AD_IMPL_PROVIDER_CLASS).kotlin.objectInstance as IAdViewFeature.Provider
        return provider.get(adDependencies)
    }

    @Provides
    @IntoSet
    @Singleton
    fun homeFeature(broker: Broker): Feature {
        val provider =
            Class.forName(HOME_IMPL_PROVIDER_CLASS).kotlin.objectInstance as IHomeFeature.Provider
        return provider.get(broker)
    }

    @Provides
    @IntoSet
    @Singleton
    fun followTopicFeature(dependencies: IFollowTopicFeature.Dependencies): Feature {
        val provider =
            Class.forName(FOLLOW_TOPIC_IMPL_PROVIDER_CLASS).kotlin.objectInstance as IFollowTopicFeature.Provider
        return provider.get(dependencies)
    }

    @Provides
    @Singleton
    fun featureRegistry(initFeatures: Set<@JvmSuppressWildcards Feature>, broker: Broker): IFeatureRegistry {
        return FeatureRegistry(initFeatures, broker)
    }

    @Provides
    @JvmStatic
    @Singleton
    fun string(): String = "aDependencyString"

    @Provides
    @JvmStatic
    @Singleton
    fun appContextProvider(application: Application): Context = application
}
