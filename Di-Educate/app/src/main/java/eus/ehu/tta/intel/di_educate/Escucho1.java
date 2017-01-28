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

public class Escucho1 extends AppCompatActivity {

    public final static String EXTRA_LOGIN= "login";
    public final static String EXTRA_USERNAME= "null";
    private static String name;
    private static String USERID="null";
    private static String boton;
    private static int stage=0;
    private static int fail=0;
    public final static String EXTRA_USERID= "null";
    private ArrayList<String> audio=null;
    private ArrayList<String> respuesta=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucho1);

        Intent intent=getIntent();
        USERID=intent.getStringExtra(EXTRA_USERID);
        name=intent.getStringExtra(EXTRA_USERNAME);

        getRespuestas();
        getAudio();

        Button button1=(Button)this.findViewById(R.id.be11);
        button1.setText("palo");
        Button button2=(Button)this.findViewById(R.id.be12);
        button2.setText("pato");
    }

    public void getRespuestas(){
        new Communication<Respuestas>(this){
            @Override
            protected Respuestas work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Respuestas respuesta= data.getRespuestas(6);
                return respuesta;
            }

            @Override
            protected void onFinish(Respuestas result) {
                respuesta=result.getRespuestas();
            }
        }.execute();
    }

    public void getAudio(){
        new Communication<Audio>(this){
            @Override
            protected Audio work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Audio url= data.getAudio(6);
                return url;
            }

            @Override
            protected void onFinish(Audio result) {
                audio=result.getAudios();
            }
        }.execute();
    }

    public void respuesta(View v){
        Button arg0 = (Button) v;
        boton=arg0.getText().toString();
        Log.d("boton",boton);
    }
    public void audio(View v) throws IOException{
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
    public void comprobar (View v){
        Log.d("correcta",respuesta.get(0));
        Button button1=(Button)this.findViewById(R.id.be11);
        Button button2=(Button)this.findViewById(R.id.be12);

        if(boton==null) {
            boton = "";
        }

        if(boton.equals(respuesta.get(0))){
            fail=0;
            boton=null;
            stage=1;
            //Cambiar audio

            //inicializar botones
            button1.setText("capucha");
            button2.setText("babucha");
        }

        else if(boton.equals(respuesta.get(1))){
            fail=0;
            boton=null;
            stage=2;
            //Cambiar audio

            //inicializar botones
            button1.setText("coche");
            button2.setText("noche");
        }
        else if(boton.equals(respuesta.get(2))){
            fail=0;
            boton=null;
            conseguido();
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
            //Log.d("AQUI","ENTRE");
            //Log.d("FAIL",Integer.toString(fail));
            if(fail==3) {
                fail=0;
                Log.d("AQUI", "ENTRE tb");
                Intent intent = new Intent(this, CorrectoActivity.class);
                Bundle extras = new Bundle();
                extras.putString("opcion", "escucho");
                extras.putString("true", "incorrecto");
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
    public void conseguido(){
        new Communication<Integer>(this){
            @Override
            protected Integer work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Integer codigo=data.postInfo(USERID ,Integer.toString(6));
                return codigo;
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println(result);
                addCalificacion();

            }
        }.execute();
    }

    public void addCalificacion() {
        new Communication<Integer>(this) {
            @Override
            protected Integer work() throws Exception {
                ObtenerDatos data = new ObtenerDatos();
                Integer codigo = data.postResultados("true");
                return codigo;
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println(result);
                correcto();
            }
        }.execute();
    }

    public void correcto(){
        Intent intent= new Intent(this,CorrectoActivity.class);
        Bundle extras=new Bundle();
        extras.putString("username",EXTRA_LOGIN);
        extras.putString("opcion","escucho");
        extras.putString("true","correcto");
        extras.putString("username",name);
        extras.putString("userid",USERID);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
