package example.musicplayerapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.zingaat);
        playButton = (Button) findViewById(R.id.playButtonID);
        seekBar = (SeekBar) findViewById(R.id.seekBarID);

        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser)
                {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    playButton.setText("Play");

                }
                else
                {
                    mediaPlayer.start();
                    playButton.setText("Pause");
                }

            }
        });
    }


    // Jo system se memory li hai vo vapas bhi to deni hai to onDestroy call honey par mediaPlayer
    // destroy karna padhega kyuli gaane vagarah badhi jagah lete hain.
    @Override
    protected void onDestroy()
    {

        if(mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onDestroy();
    }
}
