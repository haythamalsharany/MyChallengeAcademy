package com.alsharany.mychallengeacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = ChallengFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).commit()
        }
    }
}