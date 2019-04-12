package com.torch.omid.torchlight;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Policy;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {


    MediaPlayer  mMediaPlayer;
    Button btn_on;
     Button btn_off;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         btn_on = (Button) findViewById(R.id.btn_on);
          btn_off = (Button) findViewById(R.id.btn_off);

        //off
       btn_off.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               btn_on.setEnabled(true);
               btn_off.setEnabled(false);

             Camera  camera = Camera.open();
               Camera.Parameters p = camera.getParameters();
               p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
               camera.setParameters(p);
               camera.stopPreview();
           }
       });

       //on
        btn_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_on.setEnabled(false);
                btn_off.setEnabled(true);

              Camera camera = Camera.open();
                Camera.Parameters p = camera.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.startPreview();

            }
        });

        mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music_aroosakam);
        mMediaPlayer.setOnPreparedListener(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorpink)));
        }

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.colorpink));
    }



    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mMediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
