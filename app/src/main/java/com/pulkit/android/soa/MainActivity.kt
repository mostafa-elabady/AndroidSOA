package com.pulkit.android.soa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pulkit.feature.registry.features.IHomeFeature
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            // featureRegistry(this).getOfType(IHomeFeature::class.java).firstOrNull()?.let { it ->
            //     (it as IHomeFeature).show(this)
            // }
        }
    }
}
