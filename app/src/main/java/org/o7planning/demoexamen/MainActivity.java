package org.o7planning.demoexamen;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;
    private Button buttonPlay;
    private Button buttonComment;
    private EditText etText;
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.videoView = (VideoView) findViewById(R.id.videoView);
        this.buttonPlay = (Button) findViewById(R.id.button_play);

        buttonComment = (Button) findViewById(R.id.button_comment);
        etText = (EditText)  findViewById(R.id.input_comment);
        tvText = (TextView)  findViewById(R.id.view_comment);

        buttonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvText.setText(etText.getText());
            }
        });


        if (this.mediaController == null) {
            this.mediaController = new MediaController(MainActivity.this);
            this.mediaController.setAnchorView(videoView);
            this.videoView.setMediaController(mediaController);
        }

        this.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {

                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }

                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

                        mediaController.setAnchorView(videoView);

                    }
                });
            }
        });


        this.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String resName = VideoViewUtils.RAW_VIDEO_SAMPLE;
                VideoViewUtils.playRawVideo(MainActivity.this, videoView, resName);
            }
        });
    }

}