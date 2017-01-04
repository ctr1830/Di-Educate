package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class CorrectoActivity extends AppCompatActivity {

    private static String opcion;
    private static String correcto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correcto);

        //Intent intent = getIntent();
        Bundle extras=getIntent().getExtras();

        opcion = extras.getString("opcion");
        Log.d("opcion",opcion);
        correcto = extras.getString("true");
        Log.d("true/false",correcto);

        if (correcto.equals("correcto")) {
            findViewById(R.id.volvercorrecto).setVisibility(View.VISIBLE);
            PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
            pGif.setImageResource(R.drawable.correct);

        } else {
            findViewById(R.id.volvercorrecto).setVisibility(View.VISIBLE);
            PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
            findViewById(R.id.tayuda).setVisibility(View.VISIBLE);
            pGif.setImageResource(R.drawable.ayuda);
        }

    }

    public void volverCorrecto(View v){


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
    }

}