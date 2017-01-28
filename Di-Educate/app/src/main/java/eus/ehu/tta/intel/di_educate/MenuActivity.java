package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_USERNAME= "login";
    public static String EXTRA_USERID= "userid";
    private static String username;
    private static String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent =getIntent();
        username=intent.getStringExtra(EXTRA_USERNAME);
        userid=intent.getStringExtra(EXTRA_USERID);
        TextView textLogin=(TextView)findViewById(R.id.bienvenida);
        textLogin.setText("Bienvenido ".concat(username));

        System.out.println("USERID: "+userid);
        System.out.println("USERNAME: "+username);
    }

    public void leoleo(View v){
        Intent intent= new Intent(this,SubMenuActivity.class);
        Bundle extras=new Bundle();
        extras.putString("opcion","leoleo");
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void juego(View v){
        Intent intent= new Intent(this,SubMenuActivity.class);
        Bundle extras=new Bundle();
        extras.putString("opcion","juego");
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void escucho(View v){
        Intent intent= new Intent(this,SubMenuActivity.class);
        Bundle extras=new Bundle();
        extras.putString("opcion","escucho");
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
