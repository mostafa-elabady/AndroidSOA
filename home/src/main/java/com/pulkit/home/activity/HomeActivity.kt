package com.pulkit.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pulkit.android.soa.featureRegistry
import com.pulkit.feature.registry.features.IFollowTopicFeature
import com.pulkit.feature.registry.features.Topic
import com.pulkit.feature.registry.getOfType
import com.pulkit.showhomefeature.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            featureRegistry(applicationContext).getOfType<IFollowTopicFeature>().firstOrNull()
                ?.followTopic(
                    Topic("id")
                )?.subscribe()
        }
    }
}
