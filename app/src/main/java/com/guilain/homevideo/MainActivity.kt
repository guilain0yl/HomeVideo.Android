package com.guilain.homevideo

import android.app.Activity
import android.content.pm.ActivityInfo
import android.media.MediaCodecList
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.guilain.homevideo.common.GlobalConfig
import com.guilain.homevideo.fragment.PlayerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        super.onCreate(savedInstanceState)

        GlobalConfig.initScreenSize(this.resources);

        setContentView(R.layout.activity_main)

        val fs = this.supportFragmentManager.fragments;
        for (f in fs) {
            if (f is NavHostFragment) {
                navFragment = f as NavHostFragment;
                break;
            }
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val primaryFragment = navFragment.childFragmentManager.primaryNavigationFragment;
        if (primaryFragment !is PlayerFragment)
            return super.onKeyDown(keyCode, event);

        if (primaryFragment.handleKeyDown(keyCode)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private lateinit var navFragment: NavHostFragment;
}