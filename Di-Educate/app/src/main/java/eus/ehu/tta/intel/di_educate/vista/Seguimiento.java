package eus.ehu.tta.intel.di_educate.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import eus.ehu.tta.intel.di_educate.modelo.Communication;
import eus.ehu.tta.intel.di_educate.modelo.ObtenerDatos;
import eus.ehu.tta.intel.di_educate.modelo.Resultados;
import eus.ehu.tta.intel.di_educate.R;

public class Seguimiento extends AppCompatActivity {

    private String EXTRA_USERNAME= "";
    private String EXTRA_OPTION= "";
    private  String EXTRA_USERID= "";
    private ArrayList<String> resultados=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

        Bundle extras=getIntent().getExtras();
        EXTRA_OPTION=extras.getString("opcion");
        EXTRA_USERNAME=extras.getString("username");
        EXTRA_USERID=extras.getString("userid");

        PlayGifView pGif = (PlayGifView) findViewById(R.id.seguimiento);
        pGif.setImageResource(R.drawable.avanza);

        pedirDatos();
    }

    public void pedirDatos(){

        new Communication<Resultados>(this){
            @Override
            protected Resultados work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Resultados resultados= data.getResultados(Integer.parseInt(EXTRA_USERID));
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
        intent.putExtra(MenuActivity.EXTRA_USERNAME,EXTRA_USERNAME);
        intent.putExtra(MenuActivity.EXTRA_USERID,EXTRA_USERID);
        startActivity(intent);
    }

}
