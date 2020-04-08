package com.pulkit.android.soa

import android.app.Application
import android.content.Context
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
    fun adFeature(adDependencies: IAdViewFeature.Dependencies): Feature {
        val provider =
            Class.forName(AD_IMPL_PROVIDER_CLASS).kotlin.objectInstance as IAdViewFeature.Provider
        return provider.get(adDependencies)
    }

    @Provides
    @IntoSet
    fun homeFeature(): Feature {
        val provider =
            Class.forName(HOME_IMPL_PROVIDER_CLASS).kotlin.objectInstance as IHomeFeature.Provider
        return provider.get()
    }

    @Provides
    @IntoSet
    fun followTopicFeature(dependencies: IFollowTopicFeature.Dependencies): Feature {
        val provider =
            Class.forName(FOLLOW_TOPIC_IMPL_PROVIDER_CLASS).kotlin.objectInstance as IFollowTopicFeature.Provider
        return provider.get(dependencies)
    }

    @Provides
    fun featureRegistry(initFeatures: Set<@JvmSuppressWildcards Feature>): IFeatureRegistry {
        return FeatureRegistry(initFeatures)
    }

    @Provides
    @JvmStatic
    fun string(): String = "aDependencyString"

    @Provides
    @JvmStatic
    fun appContextProvider(application: Application): Context = application
}
