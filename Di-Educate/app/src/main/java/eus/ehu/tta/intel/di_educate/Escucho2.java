package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Escucho2 extends AppCompatActivity {

    private static int fail=0;
    private static String resp_correcta[]={"clavito","tigres","desenladrillador"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escucho2);

        //reproduccion audio
        TextView enun=(TextView)findViewById(R.id.e_enunciado);
        enun.setText("Pablito clavo un clavito. ¿Qué __________ clavo Pablito?");
    }

    public void comprobar(View v){
        EditText texto=(EditText)findViewById(R.id.texto);
        TextView enun=(TextView)findViewById(R.id.e_enunciado);

        if(texto.getText().toString().equals(resp_correcta[0])) {
            fail=0;
            enun.setText("Tres tristes __________ trigaban trigo en un trigal");
            texto.setText("");
        }
        else if (texto.getText().toString().equals(resp_correcta[1])){
            fail=0;
            enun.setText("El cielo esta enladrillado. ¿Quién lo desenladrillará? El _____________ que lo desenladrille, buen desenladrillador será");
            texto.setText("");
        }
        else if (texto.getText().toString().equals(resp_correcta[2])){
            fail=0;
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
