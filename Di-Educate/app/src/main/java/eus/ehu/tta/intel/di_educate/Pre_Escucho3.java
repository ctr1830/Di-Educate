package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Pre_Escucho3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preescucho3);

        //reproducir audio enunciado
    }

    public void adelante(View v){
        Intent intent= new Intent(this,Escucho3.class);
        startActivity(intent);
    }
    public void audio(View v){

    }
}
