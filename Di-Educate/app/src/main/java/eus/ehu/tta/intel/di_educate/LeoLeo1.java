package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
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

        int fail=0;
        Log.d("boton1",boton1);
        Log.d("boton2",boton2);

         if((boton1.equals("p"))&&(boton2.equals("d"))){
            fail=0;
            boton1=null;
            boton2=null;
             //Cargar nuevas imagenes
            ImageView image1=(ImageView)findViewById(R.id.foto1);
             image1.setImageResource(R.drawable.avanza);
             ImageView image2=(ImageView)findViewById(R.id.foto2);
             image2.setImageResource(R.drawable.avanza);

            Button button1=(Button)this.findViewById(R.id.bl1);
            button1.setText("d");
            Button button2=(Button)this.findViewById(R.id.bl2);
            button2.setText("b");
            Button button3=(Button)this.findViewById(R.id.bl3);
            button3.setText("d");
            Button button4=(Button)this.findViewById(R.id.bl4);
            button4.setText("b");
        }
        else if((boton1.equals("b"))&&(boton2.equals("d"))){
             fail=0;
             boton1=null;
             boton2=null;
             //Cargar nuevas imagenes
             ImageView image1=(ImageView)findViewById(R.id.foto1);
             image1.setImageResource(R.drawable.audio);
             ImageView image2=(ImageView)findViewById(R.id.foto2);
             image2.setImageResource(R.drawable.audio);

             Button button1=(Button)this.findViewById(R.id.bl1);
             button1.setText("p");
             Button button2=(Button)this.findViewById(R.id.bl2);
             button2.setText("b");
             Button button3=(Button)this.findViewById(R.id.bl3);
             button3.setText("p");
             Button button4=(Button)this.findViewById(R.id.bl4);
             button4.setText("b");

         }
         else if((boton1.equals("p"))&&(boton2.equals("b"))){
             fail=0;
             boton1=null;
             boton2=null;
             //Conseguido
             Intent intent= new Intent(this,CorrectoActivity.class);
             intent.putExtra(CorrectoActivity.EXTRA_OPCION,"leoleo");
             intent.putExtra(CorrectoActivity.EXTRA_TRUE,"correcto");
             startActivity(intent);
         }
        else{
             fail++;
             if(fail==3){
                 //Añadir audio
                 Intent intent= new Intent(this,CorrectoActivity.class);
                 intent.putExtra(CorrectoActivity.EXTRA_OPCION,"leoleo");
                 intent.putExtra(CorrectoActivity.EXTRA_TRUE,"incorrecto");
                 startActivity(intent);

             }

         }
    }

}
