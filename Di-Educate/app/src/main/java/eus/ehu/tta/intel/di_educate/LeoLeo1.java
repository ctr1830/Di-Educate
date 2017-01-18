package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Imagenes;
import Data.Respuestas;

public class LeoLeo1 extends AppCompatActivity {

    private ArrayList<String> imagenes=null;
    private ArrayList<String> respuesta=new ArrayList<String>();
    private static String boton1;
    private static String boton2;
    private static int fail=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_leo1);

        getRespuestas();
        getImagenes(0);
        getImagenes(1);
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

                ImageView imagen1=(ImageView)findViewById(R.id.foto1);
                ImageView imagen2=(ImageView)findViewById(R.id.foto2);

                if((i==1)||(i==3)||(i==5)||(i==7)){
                    imagen2.setImageBitmap(result);
                }
                else if((i==0)||(i==2)||(i==4)||(i==6)){
                    imagen1.setImageBitmap(result);
                }

            }
        }.execute();
    }

    public void getImagenes(final int i){
        new Communication<Imagenes>(this){
            @Override
            protected Imagenes work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Imagenes imagenes= data.getImagenes(1);
                return imagenes;
            }

            @Override
            protected void onFinish(Imagenes result) {
                imagenes=result.getImagenes();
                downloadImagenes(i,imagenes.get(i));
            }
        }.execute();
    }

    public void getRespuestas(){
        new Communication<Respuestas>(this){
            @Override
            protected Respuestas work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Respuestas respuesta= data.getRespuestas(1);
                return respuesta;
            }

            @Override
            protected void onFinish(Respuestas result) {
                respuesta=result.getRespuestas();
            }
        }.execute();
    }
    public void onclick1(View v){
        Button arg0 = (Button) v;
        boton1=arg0.getText().toString();
        Log.d("boton11",boton1);
    }
    public void onclick2(View v){
        Button arg0 = (Button)v;
        boton1=arg0.getText().toString();
        Log.d("boton12",boton1);
    }
    public void onclick3(View v){
        Button arg0 = (Button)v;
        boton2=arg0.getText().toString();
        Log.d("boton21",boton2);
    }
    public void onclick4(View v){
        Button arg0 = (Button)v;
        boton2=arg0.getText().toString();
        Log.d("boton22",boton2);
    }

    public void comprobar(View v){

        Button button1=(Button)this.findViewById(R.id.bl1);
        Button button2=(Button)this.findViewById(R.id.bl2);
        Button button3=(Button)this.findViewById(R.id.bl3);
        Button button4=(Button)this.findViewById(R.id.bl4);
        ImageView image1=(ImageView)findViewById(R.id.foto1);
        ImageView image2=(ImageView)findViewById(R.id.foto2);

        if((boton1==null)||(boton2==null)){
            boton1="";
            boton2="";
        }

         if((boton1.equals(respuesta.get(0)))&&(boton2.equals(respuesta.get(1)))){
            fail=0;
            boton1=null;
            boton2=null;
             //Cargar nuevas imagenes
             getImagenes(2);
             getImagenes(3);

            button1.setText("d");
            button2.setText("b");
            button3.setText("d");
            button4.setText("b");
        }
        else if((boton1.equals(respuesta.get(2)))&&(boton2.equals(respuesta.get(3)))){
             fail=0;
             boton1=null;
             boton2=null;
             //Cargar nuevas imagenes
             getImagenes(4);
             getImagenes(5);

             button1.setText("p");
             button2.setText("b");
             button3.setText("p");
             button4.setText("b");

         }
         else if((boton1.equals(respuesta.get(4)))&&(boton2.equals(respuesta.get(5)))){
             fail=0;
             boton1=null;
             boton2=null;
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
             if(fail==3){
                 fail=0;
                 Log.d("AQUI","ENTRE tb");
                 Intent intent= new Intent(this,CorrectoActivity.class);
                 Bundle extras=new Bundle();
                 extras.putString("opcion","leoleo");
                 extras.putString("true","incorrecto");
                 intent.putExtras(extras);
                 startActivity(intent);

             }

         }
    }

}
