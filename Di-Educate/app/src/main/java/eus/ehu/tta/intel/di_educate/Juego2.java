package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Juego2 extends AppCompatActivity {

    private static String boton;
    private static int fail=0;
    private static String resp_correcta[]={"pla","fan","tu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego2);

        //reproducir
        Button button1=(Button)this.findViewById(R.id.bj21);
        button1.setText("pla");
        Button button2=(Button)this.findViewById(R.id.bj22);
        button2.setText("no");
        Button button3=(Button)this.findViewById(R.id.bj23);
        button3.setText("la");
        TextView texto=(TextView)findViewById(R.id.j2pal);
        texto.setText("Explanado - Plano");
    }

    public void respuesta(View v){
        Button arg0 = (Button) v;
        boton=arg0.getText().toString();
        Log.d("boton",boton);
    }

    public void comprobar(View v){
        Button button1=(Button)this.findViewById(R.id.bj21);
        Button button2=(Button)this.findViewById(R.id.bj22);
        Button button3=(Button)this.findViewById(R.id.bj23);
        TextView texto=(TextView)findViewById(R.id.j2pal);

        if(boton.equals(resp_correcta[0])){
            fail=0;
            boton=null;
            //inicializar botones
            button1.setText("ele");
            button2.setText("ma");
            button3.setText("fan");
            texto.setText("Elefante - Fantasma");
        }

        else if(boton.equals(resp_correcta[1])){
            fail=0;
            boton=null;
            //inicializar botones
            button1.setText("ca");
            button2.setText("tu");
            button3.setText("es");
            texto.setText("Estuche - Túnica");

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
        else {
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
            if (fail == 3) {
                fail=0;
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
