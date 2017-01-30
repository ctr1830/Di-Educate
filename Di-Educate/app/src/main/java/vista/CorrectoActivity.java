package vista;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import eus.ehu.tta.intel.di_educate.R;

public class CorrectoActivity extends AppCompatActivity {

    private static String username;
    private static String userid;
    private static String opcion;
    private static String correcto;
    private static  MediaPlayer ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correcto);

        //Intent intent = getIntent();
        Bundle extras=getIntent().getExtras();

        opcion = extras.getString("opcion");
        correcto = extras.getString("true");
        username=extras.getString("username");
        userid=extras.getString("userid");

        System.out.println("USERID: "+userid);
        System.out.println("USERNAME: "+username);

        if (correcto.equals("correcto")) {
            findViewById(R.id.volvercorrecto).setVisibility(View.VISIBLE);
            PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
            pGif.setImageResource(R.drawable.correct);

            ayuda= MediaPlayer.create(this,R.raw.aplausos);
            ayuda.start();


        } else {
            findViewById(R.id.volvercorrecto).setVisibility(View.VISIBLE);
            PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
            findViewById(R.id.tayuda).setVisibility(View.VISIBLE);
            pGif.setImageResource(R.drawable.ayuda);

            ayuda= MediaPlayer.create(this,R.raw.ayuda);
            ayuda.start();
        }

    }

    protected void onPause(){
        super.onPause();
        ayuda.stop();
        ayuda.release();
    }

    public void volverCorrecto(View v){


        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_USERNAME,username);
        intent.putExtra(MenuActivity.EXTRA_USERID,userid);
        startActivity(intent);

        /*
        if(opcion.equals("leoleo")) {
            Intent mainIntent1 = new Intent(this, SubMenuActivity.class);
            mainIntent1.putExtra(SubMenuActivity.EXTRA_OPTION, "leoleo");
            startActivity(mainIntent1);
        }
        else if(opcion.equals("juego")) {
            Intent mainIntent2 = new Intent(this, SubMenuActivity.class);
            mainIntent2.putExtra(SubMenuActivity.EXTRA_OPTION, "juego");
            startActivity(mainIntent2);
        }
        else if(opcion.equals("escucho")) {
            Intent mainIntent3 = new Intent(CorrectoActivity.this, SubMenuActivity.class);
            mainIntent3.putExtra(SubMenuActivity.EXTRA_OPTION, "escucho");
            startActivity(mainIntent3);
        }
        */
    }

}