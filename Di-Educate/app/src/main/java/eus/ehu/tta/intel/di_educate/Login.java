package eus.ehu.tta.intel.di_educate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        Intent intent= new Intent(this,MenuActivity.class);
        String username =((EditText)findViewById(R.id.username)).getText().toString();
        String passwd =((EditText)findViewById(R.id.contrasena)).getText().toString();

        if(authenticate(username,passwd)){
            intent.putExtra(MenuActivity.EXTRA_LOGIN,username);
            startActivity(intent);
        }

    }
    public boolean authenticate(String username,String password){

        return true;
    }
}
