package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Audio;
import Data.Respuestas;

public class Escucho2 extends AppCompatActivity {

    public final static String EXTRA_USERID= "null";
    public final static String EXTRA_USERNAME= "null";
    private static String name;
    private static String USERID="null";
    private static int fail=0;
    private static int stage=0;
    private ArrayList<String> audio=null;
    private ArrayList<String> respuesta=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucho2);

        Intent intent=getIntent();
        USERID=intent.getStringExtra(EXTRA_USERID);
        name=intent.getStringExtra(EXTRA_USERNAME);

        getRespuesta();
        getAudio();

        //reproduccion audio
        TextView enun=(TextView)findViewById(R.id.e_enunciado);
        enun.setText("Pablito clavo un clavito. ¿Qué __________ clavo Pablito?");
    }

    public void getRespuesta(){
        new Communication<Respuestas>(this){
            @Override
            protected Respuestas work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Respuestas respuesta= data.getRespuestas(7);
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
                Audio url= data.getAudio(7);
                return url;
            }

            @Override
            protected void onFinish(Audio result) {
                audio=result.getAudios();
            }
        }.execute();
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

    public void comprobar(View v){
        EditText texto=(EditText)findViewById(R.id.texto);
        TextView enun=(TextView)findViewById(R.id.e_enunciado);

        if(texto.getText().toString().equals(respuesta.get(0))) {
            fail=0;
            stage=1;
            enun.setText("Tres tristes __________ trigaban trigo en un trigal");
            texto.setText("");
        }
        else if (texto.getText().toString().equals(respuesta.get(1))){
            fail=0;
            stage=2;
            enun.setText("El cielo esta enladrillado. ¿Quién lo desenladrillará? El _____________ que lo desenladrille, buen desenladrillador será");
            texto.setText("");
        }
        else if (texto.getText().toString().equals(respuesta.get(2))){
            fail=0;
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
                Integer codigo=data.postInfo(USERID,Integer.toString(7));
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

    public void correcto() {
        Intent intent = new Intent(this, CorrectoActivity.class);
        Bundle extras = new Bundle();
        extras.putString("opcion", "escucho");
        extras.putString("true", "correcto");
        extras.putString("username",name);
        extras.putString("userid",USERID);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
