package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Audio;
import Data.Enunciado;
import Data.Imagenes;
import Data.Respuestas;

public class Escucho3 extends AppCompatActivity {

    private static String name;
    private static String USERID="null";
    private static String boton;
    private static int fail=0;
    private static int times=0;
    private static int stage=0;
    private ArrayList<String> imagenes=null;
    private ArrayList<String> audio=null;
    private ArrayList<String> respuesta=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucho3);

        Intent intent =getIntent();
        audio=intent.getStringArrayListExtra("audio");

        Bundle extras=getIntent().getExtras();
        USERID=extras.getString("userid");
        name=extras.getString("username");

        getRespuestas();
        getImagenes(1);
        getImagenes(2);

        MediaPlayer media= MediaPlayer.create(this,R.raw.enunciado);
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
        media.start();
    }

    public void getRespuestas(){
        new Communication<Respuestas>(this){
            @Override
            protected Respuestas work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Respuestas respuesta= data.getRespuestas(8);
                return respuesta;
            }

            @Override
            protected void onFinish(Respuestas result) {
                respuesta=result.getRespuestas();
            }
        }.execute();
    }
    public void downloadImagenes(final int i, final String imagen){
        new Communication<Bitmap>(this){
            @Override
            protected Bitmap work() throws Exception{
                Bitmap bmp=null;
                try {
                    URL url=new URL(imagen);
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }catch (IOException e){
                    e.printStackTrace();
                }
                return bmp;
            }

            @Override
            protected void onFinish(Bitmap result) {

                ImageView imagen1=(ImageView)findViewById(R.id.boton1);
                ImageView imagen2=(ImageView)findViewById(R.id.boton2);
                Bitmap resized = Bitmap.createScaledBitmap(result, 300, 300, true);

                switch(i){
                    case 1:
                        imagen1.setImageBitmap(resized);
                        imagen1.setContentDescription("muu");
                        break;
                    case 2:
                        imagen2.setImageBitmap(resized);
                        imagen2.setContentDescription("guau");
                        break;
                    case 3:
                        imagen1.setImageBitmap(resized);
                        imagen1.setContentDescription("plash");
                        break;
                    case 4:
                        imagen1.setImageBitmap(resized);
                        imagen1.setContentDescription("run");
                        break;
                    case 5:
                        imagen2.setImageBitmap(resized);
                        imagen2.setContentDescription("ring");
                        break;
                    case 6:
                        imagen2.setImageBitmap(resized);
                        imagen2.setContentDescription("muack");
                        break;
                    case 7:
                        imagen1.setImageBitmap(resized);
                        imagen1.setContentDescription("boing");
                        break;

                }
            }
        }.execute();
    }

    public void getImagenes(final int i){
        new Communication<Imagenes>(this){
            @Override
            protected Imagenes work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Imagenes imagenes= data.getImagenes(8);
                return imagenes;
            }

            @Override
            protected void onFinish(Imagenes result) {
                imagenes=result.getImagenes();
                downloadImagenes(i,imagenes.get(i));
            }
        }.execute();
    }

    public void audio(View v)throws IOException{
        MediaPlayer media= new MediaPlayer();

        if(stage==0){
            media.setDataSource(audio.get(1));
        }else if(stage==1){
            media.setDataSource(audio.get(2));
        }else if (stage==2){
            media.setDataSource(audio.get(3));
        }else if(stage==3){
            media.setDataSource(audio.get(4));
        }else if (stage==4){
            media.setDataSource(audio.get(5));
        }else if (stage==5){
            media.setDataSource(audio.get(6));
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
    public void respuesta(View v){
        ImageButton arg0 = (ImageButton) v;
        boton=arg0.getContentDescription().toString();
        Log.d("boton",boton);
    }
    public void comprobar (View v){
        ImageButton imagen1=(ImageButton)findViewById(R.id.boton1);
        ImageButton imagen2=(ImageButton)findViewById(R.id.boton2);

        System.out.println("PRUEBA");
        System.out.println(boton);
        System.out.println(respuesta.get(0));

        if(boton==null) {
            boton = "";
        }

        Log.d("ENTRE","ENTRE!!");

        if((boton.equals(respuesta.get(0)))&&(times==0)){
            fail=0;
            stage++;
            times++;
            boton=null;
            getImagenes(2);
            getImagenes(3);
        }
        else if((boton.equals(respuesta.get(1)))&&(times==1)){
            fail=0;
            stage++;
            times++;
            boton=null;
            getImagenes(4);
            getImagenes(5);
        }
        else if((boton.equals(respuesta.get(2)))&&(times==2)){
            fail=0;
            stage++;
            times++;
            boton=null;
            getImagenes(2);
            getImagenes(6);
        }
        else if((boton.equals(respuesta.get(3)))&&(times==3)){
            fail=0;
            stage++;
            times++;
            boton=null;
            getImagenes(7);
            getImagenes(5);
        }
        else if((boton.equals(respuesta.get(4)))&&(times==4)){
            fail=0;
            stage++;
            times++;
            boton=null;
            getImagenes(5);
            getImagenes(1);
        }
        else if((boton.equals(respuesta.get(5)))&&(times==5)){
            fail=0;
            times++;
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
            if(fail==3) {
                fail=0;
                //Añadir audio
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
                Integer codigo=data.postInfo(USERID,Integer.toString(8));
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
        extras.putString("opcion","escucho");
        extras.putString("true","correcto");
        extras.putString("userid",USERID);
        extras.putString("username",name);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
