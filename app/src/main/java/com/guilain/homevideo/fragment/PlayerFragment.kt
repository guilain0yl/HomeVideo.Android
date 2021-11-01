package com.guilain.homevideo.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment

import android.widget.VideoView
import com.guilain.homevideo.R


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

        //view.setOnKeyListener { v, keyCode, event ->  }

        player = view.findViewById<VideoView>(R.id.video_player);
        //player.setMediaController(MediaController(this.requireContext()));
        player.setVideoURI(Uri.parse("http://static.home-video.local/video/0e09733f-ae25-4fed-bad5-a5d847a79333.mp4"));

        // player.setOnPreparedListener {  }
    }

    override fun onResume() {
        super.onResume();

        player.start();
    }

    private lateinit var player: VideoView;
}