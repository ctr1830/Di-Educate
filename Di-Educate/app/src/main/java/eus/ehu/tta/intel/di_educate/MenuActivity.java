package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_LOGIN= "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent =getIntent();
        String usuario=intent.getStringExtra(EXTRA_LOGIN);
        TextView textLogin=(TextView)findViewById(R.id.bienvenida);
        textLogin.setText("Bienvenido ".concat(usuario));
    }

    public void leoleo(View v){
        Intent intent= new Intent(this,SubMenuActivity.class);
        intent.putExtra(SubMenuActivity.EXTRA_USERNAME,EXTRA_LOGIN);
        intent.putExtra(SubMenuActivity.EXTRA_OPTION,"leoleo");
        startActivity(intent);
    }
    public void juego(View v){
        Intent intent= new Intent(this,SubMenuActivity.class);
        intent.putExtra(SubMenuActivity.EXTRA_USERNAME,EXTRA_LOGIN);
        intent.putExtra(SubMenuActivity.EXTRA_OPTION,"juego");
        startActivity(intent);
    }
    public void escucho(View v){
        Intent intent= new Intent(this,SubMenuActivity.class);
        intent.putExtra(SubMenuActivity.EXTRA_USERNAME,EXTRA_LOGIN);
        intent.putExtra(SubMenuActivity.EXTRA_OPTION,"escucho");
        startActivity(intent);
    }
}
