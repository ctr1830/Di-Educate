package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Escucho3 extends AppCompatActivity {

    private static String boton;
    private static int fail=0;
    private static int times=0;
    private static String resp_correcta[]={"mu","guau","run","muack","boing","ring"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucho3);
    }
    public void respuesta(View v){
        ImageButton arg0 = (ImageButton) v;
        boton=arg0.getContentDescription().toString();
        Log.d("boton",boton);
    }
    public void comprobar (View v){
        ImageButton imagen1=(ImageButton)findViewById(R.id.boton1);
        ImageButton imagen2=(ImageButton)findViewById(R.id.boton2);
        ImageButton audio=(ImageButton)findViewById(R.id.audio);

        Log.d("ENTRE","ENTRE!!");

        if((boton.equals(resp_correcta[0]))&&(times==0)){
            fail=0;
            times++;
            boton=null;
            //audio.setImageResource();
            imagen1.setContentDescription("guau");
            imagen2.setContentDescription("plash");
        }
        else if((boton.equals(resp_correcta[1]))&&(times==1)){
            fail=0;
            times++;
            boton=null;
            //audio.setImageResource();
            imagen1.setContentDescription("run");
            imagen2.setContentDescription("ring");
        }
        else if((boton.equals(resp_correcta[2]))&&(times==2)){
            fail=0;
            times++;
            boton=null;
            //audio.setImageResource();
            imagen1.setContentDescription("guau");
            imagen2.setContentDescription("muack");
        }
        else if((boton.equals(resp_correcta[3]))&&(times==3)){
            fail=0;
            times++;
            boton=null;
            //audio.setImageResource();
            imagen1.setContentDescription("boing");
            imagen2.setContentDescription("ring");
        }
        else if((boton.equals(resp_correcta[4]))&&(times==4)){
            fail=0;
            times++;
            boton=null;
            //audio.setImageResource();
            imagen1.setContentDescription("ring");
            imagen2.setContentDescription("mu");
        }
        else if((boton.equals(resp_correcta[5]))&&(times==5)){
            fail=0;
            times++;
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
            Log.d("AQUI", "ENTRE");
            Log.d("AQUI", Integer.toString(fail));
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
}
