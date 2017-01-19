package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Seguimiento extends AppCompatActivity {

    public final static String EXTRA_OPTION= "leoleo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

        Intent intent =getIntent();
        String opcion=intent.getStringExtra(EXTRA_OPTION);

        findViewById(R.id.volvercorrecto).setVisibility(View.VISIBLE);
        PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
        pGif.setImageResource(R.drawable.correct);

        pedirDatos();
        seguimiento(opcion);
    }

    public void pedirDatos(){

    }

    public void seguimiento(String opcion){

    }


}
