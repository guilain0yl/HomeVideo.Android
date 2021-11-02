package com.guilain.homevideo.common

import android.content.res.Resources

class GlobalConfig {
    companion object {

        fun initScreenSize(r: Resources) {
            screenHeight = r.displayMetrics.heightPixels;
            screenWidth = r.displayMetrics.widthPixels;
            screenDensityDpi = r.displayMetrics.densityDpi;
        }

        var screenWidth: Int = 0;

        var screenHeight: Int = 0;

        var screenDensityDpi: Int = 0;
    }
}