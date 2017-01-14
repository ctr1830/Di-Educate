package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Escucho1 extends AppCompatActivity {

    private static String boton;
    private static int fail=0;
    private static String resp_correcta[]={"pato","capucha","coche"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucho1);

        Button button1=(Button)this.findViewById(R.id.be11);
        button1.setText("palo");
        Button button2=(Button)this.findViewById(R.id.be12);
        button2.setText("pato");
    }

    public void respuesta(View v){
        Button arg0 = (Button) v;
        boton=arg0.getText().toString();
        Log.d("boton",boton);
    }
    public void audio(View v){

    }
    public void comprobar (View v){
        Log.d("correcta",resp_correcta[0]);
        Button button1=(Button)this.findViewById(R.id.be11);
        Button button2=(Button)this.findViewById(R.id.be12);

        if(boton==null) {
            boton = "";
        }

        if(boton.equals(resp_correcta[0])){
            fail=0;
            boton=null;
            //Cambiar audio

            //inicializar botones
            button1.setText("capucha");
            button2.setText("babucha");
        }

        else if(boton.equals(resp_correcta[1])){
            fail=0;
            boton=null;
            //Cambiar audio

            //inicializar botones
            button1.setText("coche");
            button2.setText("noche");
        }
        else if(boton.equals(resp_correcta[2])){
            fail=0;
            boton=null;
            //Conseguido
            Intent intent= new Intent(this,CorrectoActivity.class);
            Bundle extras=new Bundle();
            extras.putString("opcion","escucho");
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
}
