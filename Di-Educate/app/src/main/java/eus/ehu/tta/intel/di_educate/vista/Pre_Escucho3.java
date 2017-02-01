package eus.ehu.tta.intel.di_educate.vista;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import eus.ehu.tta.intel.di_educate.modelo.Communication;
import eus.ehu.tta.intel.di_educate.modelo.ObtenerDatos;
import eus.ehu.tta.intel.di_educate.modelo.Audio;
import eus.ehu.tta.intel.di_educate.modelo.Enunciado;
import eus.ehu.tta.intel.di_educate.R;

public class Pre_Escucho3 extends AppCompatActivity {
    private ArrayList<String> audio=null;
    private MediaPlayer media= new MediaPlayer();
    private static String name;
    private static String USERID= "null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preescucho3);

        Bundle extras=getIntent().getExtras();
        name=extras.getString("username");
        USERID=extras.getString("userid");

        getEnunciado();
        getAudio();
    }

    public void getEnunciado(){
        new Communication<Enunciado>(this){
            @Override
            protected Enunciado work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Enunciado texto= data.getEnunciados(8);
                return texto;
            }

            @Override
            protected void onFinish(Enunciado result) {

                TextView texto=(TextView)findViewById(R.id.enunciado);
                texto.setText(result.getEnunciados().get(0));
            }
        }.execute();
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
        Bundle extras=new Bundle();
        media.stop();
        media.release();
        intent.putStringArrayListExtra("audio",audio);

        extras.putString("username",name);
        extras.putString("userid",USERID);
        intent.putExtras(extras);
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
