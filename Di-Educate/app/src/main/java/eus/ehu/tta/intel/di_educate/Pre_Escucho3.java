package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Audio;

public class Pre_Escucho3 extends AppCompatActivity {
    private ArrayList<String> audio=null;
    private MediaPlayer media= new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preescucho3);

        getAudio();
    }

    public void getAudio(){
        new Communication<Audio>(this){
            @Override
            protected Audio work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Audio url= data.getAudio(8);
                return url;
            }

            @Override
            protected void onFinish(Audio result) {
                audio=result.getAudios();
            }
        }.execute();
    }
    public void adelante(View v){
        Intent intent= new Intent(this,Escucho3.class);
        media.stop();
        media.release();
        intent.putStringArrayListExtra("audio",audio);
        startActivity(intent);
    }
    public void audio(View v)throws IOException{
        media.setDataSource(audio.get(0));
        media.prepare();
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
        media.start();
    }
}
