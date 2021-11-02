package com.guilain.homevideo.fragment

import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

import android.widget.VideoView
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.guilain.homevideo.R
import com.guilain.homevideo.common.GlobalConfig
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread


class PlayerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private fun initView(view: View) {
        player = view.findViewById(R.id.video_player);
        player.setVideoURI(Uri.parse("http://static.home-video.local/video/0e09733f-ae25-4fed-bad5-a5d847a79333.mp4"));

        progress = view.findViewById(R.id.video_progress);
        progress.progress = 0;
        progress.layoutParams.width = (GlobalConfig.screenWidth * 0.9).toInt();

        button = view.findViewById<ImageView>(R.id.play_pause_button);
        button.layoutParams.width = (GlobalConfig.screenWidth / 25.6).toInt();
        button.layoutParams.height = button.layoutParams.width;

        player.setOnPreparedListener {
            progress.max = (it.duration / 1000);
        }
    }

    override fun onResume() {
        super.onResume();

        player.start();

        autoGone();
        autoPlay();
    }

    override fun onPause() {
        super.onPause();

        leave = true;
    }

    fun handleKeyDown(keyCode: Int): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                progress.progress = player.currentPosition / 1000;
                display(progress);
                return true;
            };
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                if (player.isPlaying) {
                    button.visibility = ImageView.VISIBLE;
                    player.pause();
                } else {
                    button.visibility = ImageView.GONE;
                    player.start();
                }

                return true;
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                keyDownFlag = true;
                lastKeyDownTime = System.currentTimeMillis();

                if (progress.progress - 10 < 0)
                    progress.progress = 0;
                else
                    progress.progress -= 10;

                display(progress);
                return true;
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                keyDownFlag = true;
                lastKeyDownTime = System.currentTimeMillis();

                if (progress.progress + 10 > progress.max)
                    progress.progress = progress.max;
                else
                    progress.progress += 10;

                display(progress);
                return true;
            }
            else -> return false;
        }
    }

    private fun display(view: View) {
        lastDisplay = System.currentTimeMillis();

        if (view.visibility != View.VISIBLE)
            view.visibility = View.VISIBLE;
    }

    private fun autoGone() {
        doAsync {
            while (!leave) {
                Thread.sleep(1000);

                if (System.currentTimeMillis() > lastDisplay + 3000) {
                    runOnUiThread {
                        progress.visibility = View.GONE;
                    }
                }
            }
        }
    }

    private fun autoPlay() {
        doAsync {
            while (!leave) {
                Thread.sleep(1000);

                if (keyDownFlag) {
                    if (System.currentTimeMillis() > lastKeyDownTime + 600) {
                        keyDownFlag = false;
                        player.pause();
                        var seek = progress.progress * 1000;
                        if (seek > player.duration)
                            seek = player.duration;
                        player.seekTo(seek);
                        player.start();
                    }
                }
            }
        }
    }

    private var leave: Boolean = false;

    private var lastDisplay: Long = 0;

    private var lastKeyDownTime: Long = 0;

    private var keyDownFlag: Boolean = false;

    private lateinit var player: VideoView;

    private lateinit var progress: ProgressBar;

    private lateinit var button: ImageView;
}