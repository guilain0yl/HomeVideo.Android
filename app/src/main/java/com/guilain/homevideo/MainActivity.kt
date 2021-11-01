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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val list = MediaCodecList(MediaCodecList.REGULAR_CODECS).codecInfos
//        for (codec in list) {
//            val msg =
//                "name:${codec.name}.isEncoder:${codec.isEncoder}.isHardwareAccelerated:${codec.supportedTypes.joinToString()}";
//            Log.e("编解码：", msg);
//        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_ENTER ->
                Log.e("", "KEYCODE_ENTER");
            KeyEvent.KEYCODE_DPAD_UP ->
                Log.e("", "KEYCODE_DPAD_UP");
            KeyEvent.KEYCODE_DPAD_DOWN ->
                Log.e("", "KEYCODE_DPAD_DOWN");
            KeyEvent.KEYCODE_DPAD_LEFT ->
                Log.e("", "KEYCODE_DPAD_LEFT");
            KeyEvent.KEYCODE_DPAD_RIGHT ->
                Log.e("", "KEYCODE_DPAD_RIGHT");
            KeyEvent.KEYCODE_BACK ->
                Log.e("", "KEYCODE_BACK");
            KeyEvent.KEYCODE_INFO ->
                Log.e("", "KEYCODE_INFO");
            KeyEvent.KEYCODE_DPAD_CENTER ->
                Log.e("", "KEYCODE_DPAD_CENTER");
        }

        return super.onKeyDown(keyCode, event);
    }
}