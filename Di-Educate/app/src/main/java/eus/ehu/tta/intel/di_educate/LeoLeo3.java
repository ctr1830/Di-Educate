package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LeoLeo3 extends AppCompatActivity {
    private static String boton;
    private static int fail=0;
    private static String resp_correcta1[]={"lagarto","tomate","tetera"};
    private static String resp_correcta2[]={"lechuga","gato","topo"};
    private static String resp_correcta3[]={"cinco","copa","papel"};
    private static ArrayList<String> array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_leo3);

        Button button1=(Button)this.findViewById(R.id.bl31);
        button1.setText("tomate");
        Button button2=(Button)this.findViewById(R.id.bl32);
        button2.setText("lagarto");
        Button button3=(Button)this.findViewById(R.id.bl33);
        button3.setText("tetera");
        TextView textLogin=(TextView)findViewById(R.id.palabra);
        textLogin.setText(resp_correcta1[0].toUpperCase());

    }
    public void respuesta(View v){
        Button arg0 = (Button) v;
        boton=arg0.getText().toString();
        Log.d("boton",boton);

        array.add(boton);
    }
    public void comprobar3(View v){

        Button button1=(Button)this.findViewById(R.id.bl31);
        Button button2=(Button)this.findViewById(R.id.bl32);
        Button button3=(Button)this.findViewById(R.id.bl33);
        TextView textLogin=(TextView)findViewById(R.id.palabra);

        Log.d("ARRAY",array.get(0));
        Log.d("ARRAY",array.get(1));
        Log.d("ARRAY",array.get(2));


        if((resp_correcta1[0].equals(array.get(0)))&&(resp_correcta1[1].equals(array.get(1)))&&(resp_correcta1[2].equals(array.get(2)))) {
            fail = 0;
            boton = null;
            array.clear();

            //inicializar botones
            button1.setText("gato");
            button2.setText("topo");
            button3.setText("lechuga");
            textLogin.setText(resp_correcta2[0].toUpperCase());
        }
        else if((resp_correcta2[0].equals(array.get(0)))&&(resp_correcta2[1].equals(array.get(1)))&&(resp_correcta2[2].equals(array.get(2)))){
            fail = 0;
            boton = null;
            array.clear();
            //inicializar botones
            button1.setText("cinco");
            button2.setText("papel");
            button3.setText("copa");
            textLogin.setText(resp_correcta3[0].toUpperCase());
        }

        else if((resp_correcta3[0].equals(array.get(0)))&&(resp_correcta3[1].equals(array.get(1)))&&(resp_correcta3[2].equals(array.get(2)))){
            fail = 0;
            boton = null;
            array.clear();

            Intent intent= new Intent(this,CorrectoActivity.class);
            Bundle extras=new Bundle();
            extras.putString("opcion","leoleo");
            extras.putString("true","correcto");
            intent.putExtras(extras);
            startActivity(intent);
        }
        else{
            fail++;
            boton = null;
            array.clear();
            
            //Log.d("AQUI","ENTRE");
            //Log.d("FAIL",Integer.toString(fail));
            if(fail==3) {
                //Añadir audio
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
