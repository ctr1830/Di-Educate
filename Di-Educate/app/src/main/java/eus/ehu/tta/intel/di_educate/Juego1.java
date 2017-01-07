package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Juego1 extends AppCompatActivity {

    private static String boton;
    private static int fail=0;
    private static String resp_correcta[]={"go","bu","lo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego1);

        Button button1=(Button)this.findViewById(R.id.bj11);
        button1.setText("bo");
        Button button2=(Button)this.findViewById(R.id.bj12);
        button2.setText("go");
        Button button3=(Button)this.findViewById(R.id.bj13);
        button3.setText("do");
        TextView texto=(TextView)findViewById(R.id.pal);
        texto.setText("Fri__rífico");
    }

    public void respuesta(View v){
        Button arg0 = (Button) v;
        boton=arg0.getText().toString();
        Log.d("boton",boton);
    }

    public void comprobar(View v){

        Button button1=(Button)this.findViewById(R.id.bj11);
        Button button2=(Button)this.findViewById(R.id.bj12);
        Button button3=(Button)this.findViewById(R.id.bj13);
        TextView texto=(TextView)findViewById(R.id.pal);

        if(boton.equals(resp_correcta[0])){
            fail=0;
            boton=null;
            //Cambiar audio

            //inicializar botones
            button1.setText("bu");
            button2.setText("gu");
            button3.setText("du");
            texto.setText("A__elo");
        }

        else if(boton.equals(resp_correcta[1])){
            fail=0;
            boton=null;
            //inicializar botones
            button1.setText("go");
            button2.setText("po");
            button3.setText("lo");
            texto.setText("Pe__ta");

        }
        else if(boton.equals(resp_correcta[2])){
            fail=0;
            boton=null;
            //Conseguido
            Intent intent= new Intent(this,CorrectoActivity.class);
            Bundle extras=new Bundle();
            extras.putString("opcion","juego");
            extras.putString("true","correcto");
            intent.putExtras(extras);
            startActivity(intent);
        }
        else{
            fail++;

            //Log.d("AQUI","ENTRE");
            //Log.d("FAIL",Integer.toString(fail));
            if(fail==3) {
                //Añadir audio
                Log.d("AQUI", "ENTRE tb");
                Intent intent = new Intent(this, CorrectoActivity.class);
                Bundle extras = new Bundle();
                extras.putString("opcion", "juego");
                extras.putString("true", "incorrecto");
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}