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

    public static String EXTRA_OPCION = "";
    public static String EXTRA_TRUE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correcto);

        Intent intent = getIntent();
        String opcion = intent.getStringExtra(EXTRA_OPCION);
        String correcto = intent.getStringExtra(EXTRA_TRUE);

        if (correcto.equals("correcto")) {
            PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
            pGif.setImageResource(R.drawable.correct);
            findViewById(R.id.volvercorrecto);

        } else {
            PlayGifView pGif = (PlayGifView) findViewById(R.id.correcto);
            findViewById(R.id.tayuda).setVisibility(View.VISIBLE);
            pGif.setImageResource(R.drawable.ayuda);
        }

    }

    public void volverCorrecto(View v){

        if(EXTRA_OPCION.equals("leoleo")) {
            Intent mainIntent1 = new Intent(CorrectoActivity.this, SubMenuActivity.class);
            mainIntent1.putExtra(SubMenuActivity.EXTRA_OPTION, "leoleo");
            startActivity(mainIntent1);
        }
        else if(EXTRA_OPCION.equals("juego")) {
            Intent mainIntent2 = new Intent(CorrectoActivity.this, SubMenuActivity.class);
            mainIntent2.putExtra(SubMenuActivity.EXTRA_OPTION, "juego");
            startActivity(mainIntent2);
        }
        else if(EXTRA_OPCION.equals("escucho")) {
            Intent mainIntent3 = new Intent(CorrectoActivity.this, SubMenuActivity.class);
            mainIntent3.putExtra(SubMenuActivity.EXTRA_OPTION, "escucho");
            startActivity(mainIntent3);
        }
    }
    
}