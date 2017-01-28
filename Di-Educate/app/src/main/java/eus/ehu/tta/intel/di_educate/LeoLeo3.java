package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Business.Communication;
import Business.ObtenerDatos;
import Data.Enunciado;
import Data.Respuestas;

public class LeoLeo3 extends AppCompatActivity {
    private static String boton;
    private static int fail=0;
    private ArrayList<String> respuestas=null;
    public final static String EXTRA_USERID= "null";
    public final static String EXTRA_USERNAME= "null";
    private static String name;
    private static String USERID= "null";
    private static ArrayList<String> array = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leo_leo3);

        getEnunciado();
        getRespuestas();

        Intent intent=getIntent();
        USERID=intent.getStringExtra(EXTRA_USERID);
        name=intent.getStringExtra(EXTRA_USERNAME);
        Button button1=(Button)this.findViewById(R.id.bl31);
        button1.setText("tomate");
        Button button2=(Button)this.findViewById(R.id.bl32);
        button2.setText("lagarto");
        Button button3=(Button)this.findViewById(R.id.bl33);
        button3.setText("tetera");

    }

    public void getEnunciado(){
        new Communication<Enunciado>(this){
            @Override
            protected Enunciado work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Enunciado texto= data.getEnunciados(3);
                return texto;
            }

            @Override
            protected void onFinish(Enunciado result) {

                TextView texto=(TextView)findViewById(R.id.l_enunciado);
                texto.setText(result.getEnunciados().get(0));
            }
        }.execute();
    }

    public void getRespuestas(){
        new Communication<Respuestas>(this){
            @Override
            protected Respuestas work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Respuestas respuesta= data.getRespuestas(3);
                return respuesta;
            }

            @Override
            protected void onFinish(Respuestas result) {
                respuestas=result.getRespuestas();
                TextView textLogin=(TextView)findViewById(R.id.palabra);
                textLogin.setText(respuestas.get(0).toUpperCase());
            }
        }.execute();
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

        //Log.d("ARRAY",array.get(0));
        //Log.d("ARRAY",array.get(1));
        //Log.d("ARRAY",array.get(2));
        if (boton==null){
            array.add("");
        }
        if(array.size()!=3){
            array.add("");
            array.add("");
            array.add("");
        }

        if((respuestas.get(0).equals(array.get(0)))&&(respuestas.get(1).equals(array.get(1)))&&(respuestas.get(2).equals(array.get(2)))) {
            fail = 0;
            boton = null;
            array.clear();

            //inicializar botones
            button1.setText("gato");
            button2.setText("topo");
            button3.setText("lechuga");
            textLogin.setText(respuestas.get(3).toUpperCase());
        }
        else if((respuestas.get(3).equals(array.get(0)))&&(respuestas.get(4).equals(array.get(1)))&&(respuestas.get(5).equals(array.get(2)))){
            fail = 0;
            boton = null;
            array.clear();
            //inicializar botones
            button1.setText("cinco");
            button2.setText("papel");
            button3.setText("copa");
            textLogin.setText(respuestas.get(6).toUpperCase());
        }

        else if((respuestas.get(6).equals(array.get(0)))&&(respuestas.get(7).equals(array.get(1)))&&(respuestas.get(8).equals(array.get(2)))){
            fail = 0;
            boton = null;
            array.clear();

            conseguido();
        }
        else{
            fail++;
            boton = null;
            array.clear();
            MediaPlayer media= MediaPlayer.create(this,R.raw.fail);
            media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.stop();
                    mp.release();
                }
            });
            media.start();
            if(fail==3) {
                fail=0;
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
    public void conseguido(){
        new Communication<Integer>(this){
            @Override
            protected Integer work() throws Exception{
                ObtenerDatos data = new ObtenerDatos();
                Integer codigo=data.postInfo(USERID,Integer.toString(3));
                return codigo;
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println(result);
                addCalificacion();

            }
        }.execute();
    }

    public void addCalificacion() {
        new Communication<Integer>(this) {
            @Override
            protected Integer work() throws Exception {
                ObtenerDatos data = new ObtenerDatos();
                Integer codigo = data.postResultados("true");
                return codigo;
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println(result);
                correcto();
            }
        }.execute();
    }

    public void correcto(){
        Intent intent= new Intent(this,CorrectoActivity.class);
        Bundle extras=new Bundle();
        extras.putString("opcion","leoleo");
        extras.putString("true","correcto");
        extras.putString("username",name);
        extras.putString("userid",USERID);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
