package vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import modelo.Communication;
import modelo.ObtenerDatos;
import eus.ehu.tta.intel.di_educate.R;

public class Login extends AppCompatActivity {

    private static String userid="null";
    private static String username;
    private static String pass;
    private static String cadena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){

        username =((EditText)findViewById(R.id.username)).getText().toString();
        pass =((EditText)findViewById(R.id.contrasena)).getText().toString();

        authenticate();

    }
    public void authenticate(){
        new Communication<Integer>(this) {
            @Override
            protected Integer work() throws Exception {

                ObtenerDatos data = new ObtenerDatos();
                String respuesta = data.postRequestUser(username,pass);
                String[] array=respuesta.split(";");

                if(array.length==1){
                    System.out.println("respuesta: "+ array[0]);
                    cadena=array[0];
                }
                else if(array.length==2) {
                    cadena=array[0];
                    userid = array[1];
                }

                if(userid.equals("null")){
                    cadena=array[0];
                    return 0;
                }
                else {
                    return 200;
                }
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println("USERID: "+userid);
                if(result.equals(0)){
                    Toast.makeText(Login.this,cadena,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this, cadena, Toast.LENGTH_SHORT).show();
                    menuActivity(userid);
                }
            }
        }.execute();
    }

    public void menuActivity(String userid){
        Intent intent= new Intent(this,MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_USERNAME,username);
        intent.putExtra(MenuActivity.EXTRA_USERID,userid);
        startActivity(intent);
    }
}
