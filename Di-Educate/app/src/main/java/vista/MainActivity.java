package vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import eus.ehu.tta.intel.di_educate.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v){
        Intent intent= new Intent(this,Login.class);
        startActivity(intent);
    }

    public void newAccount(View v){
        Intent intent= new Intent(this,NewAccount.class);
        startActivity(intent);
    }
}
