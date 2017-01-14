package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LeoLeo1 extends AppCompatActivity {

    private static String boton1;
    private static String boton2;
    private static int fail=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_leo1);
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

         if((boton1.equals("p"))&&(boton2.equals("d"))){
            fail=0;
            boton1=null;
            boton2=null;
             //Cargar nuevas imagenes

             image1.setImageResource(R.drawable.avanza);
             image2.setImageResource(R.drawable.avanza);

            button1.setText("d");
            button2.setText("b");
            button3.setText("d");
            button4.setText("b");
        }
        else if((boton1.equals("b"))&&(boton2.equals("d"))){
             fail=0;
             boton1=null;
             boton2=null;
             //Cargar nuevas imagenes
             image1.setImageResource(R.drawable.audio);
             image2.setImageResource(R.drawable.audio);

             button1.setText("p");
             button2.setText("b");
             button3.setText("p");
             button4.setText("b");

         }
         else if((boton1.equals("p"))&&(boton2.equals("b"))){
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
