package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;

public class SubMenuActivity extends AppCompatActivity {
    //public final static String EXTRA_USERNAME= "login";
    public final static String EXTRA_OPTION= "leoleo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        Intent intent =getIntent();
        String opcion=intent.getStringExtra(EXTRA_OPTION);

         submenu(opcion);

    }

    public void submenu(String opcion){
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_sub_menu);
        MediaPlayer media=new MediaPlayer();
        switch(opcion){
            case "leoleo":
                layout.removeView(findViewById(R.id.escucho1));
                layout.removeView(findViewById(R.id.escucho2));
                layout.removeView(findViewById(R.id.escucho3));
                layout.removeView(findViewById(R.id.juego1));
                layout.removeView(findViewById(R.id.juego2));
                layout.removeView(findViewById(R.id.seguimiento2));
                layout.removeView(findViewById(R.id.seguimiento3));
                layout.setBackgroundResource(R.drawable.fondo2);
                setTitle("Leo Leo");

                media.create(this,R.raw.leoleo);
                media.setAudioStreamType(AudioManager.STREAM_MUSIC);
                media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                break;
            case "juego":
                layout.removeView(findViewById(R.id.leoleo1));
                layout.removeView(findViewById(R.id.leoleo2));
                layout.removeView(findViewById(R.id.leoleo3));
                layout.removeView(findViewById(R.id.escucho1));
                layout.removeView(findViewById(R.id.escucho2));
                layout.removeView(findViewById(R.id.escucho3));
                layout.removeView(findViewById(R.id.seguimiento1));
                layout.removeView(findViewById(R.id.seguimiento3));
                setTitle("Juego con las palabras");
                layout.setBackgroundResource(R.drawable.fondo3);
                media.create(this,R.raw.juego);
                media.setAudioStreamType(AudioManager.STREAM_MUSIC);
                media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                break;
            case "escucho":
                layout.removeView(findViewById(R.id.leoleo1));
                layout.removeView(findViewById(R.id.leoleo2));
                layout.removeView(findViewById(R.id.leoleo3));
                layout.removeView(findViewById(R.id.juego1));
                layout.removeView(findViewById(R.id.juego2));
                layout.removeView(findViewById(R.id.seguimiento1));
                layout.removeView(findViewById(R.id.seguimiento2));
                setTitle("Escucho mi voz");
                layout.setBackgroundResource(R.drawable.fondo4);
                media.create(this,R.raw.escucho);
                media.setAudioStreamType(AudioManager.STREAM_MUSIC);
                media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
                break;


        }
    }

    public void leoleo1(View v){
        Intent intent= new Intent(this,LeoLeo1.class);
        startActivity(intent);
    }
    public void leoleo2(View v){
        Intent intent= new Intent(this,LeoLeo2.class);
        startActivity(intent);
    }
    public void leoleo3(View v){
        Intent intent= new Intent(this,LeoLeo3.class);
        startActivity(intent);
    }
/*
    @Override
    protected void onDestroy() {
        media.stop();
        media.release();
        super.onDestroy();
    }
*/
}
