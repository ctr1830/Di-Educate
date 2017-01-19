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

    public final static String EXTRA_OPTION= "";
    public final static int EXTRA_USERID= 15;
    private ArrayList<String> resultados=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

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
        Intent intent =getIntent();
        String opcion=intent.getStringExtra(EXTRA_OPTION);

        TextView texto1=(TextView)findViewById(R.id.seg1);
        TextView texto2=(TextView)findViewById(R.id.seg2);
        TextView texto3=(TextView)findViewById(R.id.seg3);
        String aprobado="COMPLETADO";
        String no_aprobado="INCOMPLETO";

        switch(opcion){
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
        if(EXTRA_OPTION.equals("leoleo")) {
            Intent mainIntent1 = new Intent(this, SubMenuActivity.class);
            mainIntent1.putExtra(SubMenuActivity.EXTRA_OPTION, "leoleo");
            startActivity(mainIntent1);
        }
        else if(EXTRA_OPTION.equals("juego")) {
            Intent mainIntent2 = new Intent(this, SubMenuActivity.class);
            mainIntent2.putExtra(SubMenuActivity.EXTRA_OPTION, "juego");
            startActivity(mainIntent2);
        }
        else if(EXTRA_OPTION.equals("escucho")) {
            Intent mainIntent3 = new Intent(this, SubMenuActivity.class);
            mainIntent3.putExtra(SubMenuActivity.EXTRA_OPTION, "escucho");
            startActivity(mainIntent3);
        }
    }

}
