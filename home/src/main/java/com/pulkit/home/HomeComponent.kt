package com.pulkit.home

import dagger.Component
import dagger.Module

@Component
interface HomeComponent {
    fun homeFeature(): HomeFeature
}


@Module
class HomeModule {

}