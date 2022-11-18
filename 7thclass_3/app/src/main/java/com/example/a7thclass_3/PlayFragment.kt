package com.example.a7thclass_3

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.a7thclass_3.databinding.FragmentLibraryBinding
import com.example.a7thclass_3.databinding.FragmentPlayBinding


class PlayFragment : Fragment() {
    private lateinit var viewBinding : FragmentPlayBinding
    lateinit var runnable : Runnable
    private var handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentPlayBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mediaPlayer : MediaPlayer = MediaPlayer.create(activity, R.raw.clap_and_yell)
        viewBinding.musicName.setText("Clap and Yell")

        //seekbar의 속성 중 pro
        viewBinding.seekbar.progress = 0
        //max는 seekBar의 최댓값
        viewBinding.seekbar.max = mediaPlayer.duration  //seekbar의 최댓값은 mediaPlayer기준
        //여기서는 하지 않았지만 thumb는 커서의 이미지

        //play버튼을 눌렀을 때
        viewBinding.playBtn.setOnClickListener {
            if(!mediaPlayer.isPlaying) {    //음악이 작동중이 아니라면
                mediaPlayer.start()
                viewBinding.playBtn.setImageResource(R.drawable.pause_btn)
            } else if(mediaPlayer.isPlaying) {    //음악이 작동중이라면
                mediaPlayer.pause()
                viewBinding.playBtn.setImageResource(R.drawable.play_btn)
            }
        }

        //seekBar를 직접 조작할 때때
        viewBinding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            //seekBar를 조작하고 있는 중 작동
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed) {
                    mediaPlayer.seekTo(pos)
                }
            }
            //seekBar를 조작하기 시작했을 떄 작동
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            //seekBar 조작을 마무리했을 때 작동
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            //seekBar의 progress를 음악의 현재 상태값으로 넣는다
            viewBinding.seekbar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        //음악이 종료되었을 때
        mediaPlayer.setOnCompletionListener {
            viewBinding.playBtn.setImageResource(R.drawable.play_btn)
            viewBinding.seekbar.progress = 0
        }
    }
}