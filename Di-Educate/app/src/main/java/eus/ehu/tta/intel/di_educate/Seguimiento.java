package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Respuestas;
import Data.Resultados;

public class Seguimiento extends AppCompatActivity {

    private String EXTRA_LOGIN= "";
    private String EXTRA_OPTION= "";
    public final static int EXTRA_USERID= 16;
    private ArrayList<String> resultados=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

        Bundle extras=getIntent().getExtras();
        EXTRA_OPTION=extras.getString("opcion");
        EXTRA_LOGIN=extras.getString("username");
        //CONSEGUIR USERID

        PlayGifView pGif = (PlayGifView) findViewById(R.id.seguimiento);
        pGif.setImageResource(R.drawable.avanza);

        pedirDatos();
    }

    public void pedirDatos(){

        new Communication<Resultados>(this){
            @Override
            protected Resultados work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Resultados resultados= data.getResultados(EXTRA_USERID);
                return resultados;
            }

            @Override
            protected void onFinish(Resultados result) {
                resultados=result.getResultados();
                mostrarResultados();
            }
        }.execute();
    }

    public void mostrarResultados(){


        TextView texto1=(TextView)findViewById(R.id.seg1);
        TextView texto2=(TextView)findViewById(R.id.seg2);
        TextView texto3=(TextView)findViewById(R.id.seg3);
        String aprobado="COMPLETADO";
        String no_aprobado="INCOMPLETO";

        switch(EXTRA_OPTION){
            case "leoleo":
                if(resultados.get(0).equals("true")){
                    texto1.setText("Leo Leo 1: "+aprobado);
                }
                else{
                    texto1.setText("Leo Leo 1: "+no_aprobado);
                }
                if(resultados.get(1).equals("true")){
                    texto2.setText("Leo Leo 2: "+aprobado);
                }
                else{
                    texto2.setText("Leo Leo 2: "+no_aprobado);
                }
                if(resultados.get(2).equals("true")){
                    texto3.setText("Leo Leo 3: "+aprobado);
                }
                else{
                    texto3.setText("Leo Leo 3: "+no_aprobado);
                }
                break;
            case "juego":
                texto3.setText("");
                if(resultados.get(3).equals("true")){
                    texto1.setText("Juego con las palabras 1: "+aprobado);
                }
                else{
                    texto1.setText("Juego con las palabras 1: "+no_aprobado);
                }
                if(resultados.get(4).equals("true")){
                    texto2.setText("Juego con las palabras 2: "+aprobado);
                }
                else{
                    texto2.setText("Juego con las palabras 2: "+no_aprobado);
                }
                break;
            case "escucho":
                if(resultados.get(5).equals("true")){
                    texto1.setText("Escucho mi voz 1: "+aprobado);
                }
                else{
                    texto1.setText("Escucho mi voz 1: "+no_aprobado);
                }
                if(resultados.get(6).equals("true")){
                    texto2.setText("Escucho mi voz 2: "+aprobado);
                }
                else{
                    texto2.setText("Escucho mi voz 2: "+no_aprobado);
                }
                if(resultados.get(7).equals("true")){
                    texto3.setText("Escucho mi voz 3: "+aprobado);
                }
                else{
                    texto3.setText("Escucho mi voz 3: "+no_aprobado);
                }
                break;
        }
    }

    public void volver(View v){
        Intent intent= new Intent(this,MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_LOGIN,EXTRA_LOGIN);
        startActivity(intent);
    }

}
