package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Audio;
import Data.Respuestas;

public class LeoLeo2 extends AppCompatActivity {

    private static String boton;
    private static int fail=0;
    private static int stage=0;
    private ArrayList<String> audio=null;
    private ArrayList<String> respuesta=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_leo2);

        getRespuestas();
        getAudio();

        Button button1=(Button)this.findViewById(R.id.bl21);
        button1.setText("melon");
        Button button2=(Button)this.findViewById(R.id.bl22);
        button2.setText("raton");
        Button button3=(Button)this.findViewById(R.id.bl23);
        button3.setText("camion");
        Button button4=(Button)this.findViewById(R.id.bl24);
        button4.setText("platon");
    }

    public void getAudio(){
        new Communication<Audio>(this){
            @Override
            protected Audio work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Audio url= data.getAudio(2);
                return url;
            }

            @Override
            protected void onFinish(Audio result) {
                audio=result.getAudios();
            }
        }.execute();
    }

    public void getRespuestas(){
        new Communication<Respuestas>(this){
            @Override
            protected Respuestas work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Respuestas respuesta= data.getRespuestas(2);
                return respuesta;
            }

            @Override
            protected void onFinish(Respuestas result) {
                respuesta=result.getRespuestas();
            }
        }.execute();
    }

    public void respuesta(View v){
        Button arg0 = (Button) v;
        boton=arg0.getText().toString();
        Log.d("boton",boton);
    }
    public void audio(View v) throws IOException {
        MediaPlayer media= new MediaPlayer();

        if(stage==0){
            media.setDataSource(audio.get(0));
        }else if(stage==1){
            media.setDataSource(audio.get(1));
        }else if (stage==2){
            media.setDataSource(audio.get(2));
        }

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
    public void comprobar2(View v){

        Log.d("correcta",respuesta.get(0));
        Button button1=(Button)this.findViewById(R.id.bl21);
        Button button2=(Button)this.findViewById(R.id.bl22);
        Button button3=(Button)this.findViewById(R.id.bl23);
        Button button4=(Button)this.findViewById(R.id.bl24);

        if(boton==null) {
            boton = "";
        }

        if(boton.equals(respuesta.get(0))){
            fail=0;
            boton=null;
            stage=1;
            //Cambiar audio

            //inicializar botones
            button1.setText("atropellar");
            button2.setText("trazar");
            button3.setText("callar");
            button4.setText("titubear");
        }

         else if(boton.equals(respuesta.get(1))){
            fail=0;
            stage=2;
            boton=null;
            //Cambiar audio

            //inicializar botones
            button1.setText("ciruelo");
            button2.setText("hoyuelo");
            button3.setText("polluelo");
            button4.setText("abuelo");

        }
        else if(boton.equals(respuesta.get(2))){
            fail=0;
            boton=null;
            //Conseguido
            Intent intent= new Intent(this,CorrectoActivity.class);
            Bundle extras=new Bundle();
            extras.putString("opcion","leoleo");
            extras.putString("true","correcto");
            intent.putExtras(extras);
            startActivity(intent);
        }
        else{
            fail++;
            MediaPlayer media= MediaPlayer.create(this,R.raw.fail);
            media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();
                }
            });
            media.start();
            if(fail==3) {
                fail=0;
                Log.d("AQUI", "ENTRE tb");
                Intent intent = new Intent(this, CorrectoActivity.class);
                Bundle extras = new Bundle();
                extras.putString("opcion", "leoleo");
                extras.putString("true", "incorrecto");
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}
