package eus.ehu.tta.intel.di_educate;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import Business.Communication;
import Business.ObtenerDatos;

public class NewAccount extends AppCompatActivity {
    public final static int PICTURE_REQUEST_CODE=1;
    private String respuesta;
    private String name="null";
    private String surname="null";
    private String age="null";
    private String hobbies="null";
    private String picture="null";
    private String username="null";
    private String password="null";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
    }

    public void sacarFoto(View v){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this,R.string.no_camera,Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                try {
                    File file = File.createTempFile("di-educate", ".jpg", dir);
                    Uri pictureUri = Uri.fromFile(file);
                    picture=pictureUri.toString();
                    picture=picture.substring(24);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    public void createAccount(View v){

        EditText nombre=(EditText)findViewById(R.id.nombre);
        name=nombre.getText().toString();
        EditText apellidos=(EditText)findViewById(R.id.apellidos);
        surname=apellidos.getText().toString();
        EditText edad=(EditText)findViewById(R.id.edad);
        age=edad.getText().toString();
        EditText aficiones=(EditText)findViewById(R.id.aficiones);
        hobbies=aficiones.getText().toString();
        EditText user=(EditText)findViewById(R.id.nombre_usuario);
        username=user.getText().toString();
        EditText pass=(EditText)findViewById(R.id.password);
        password=pass.getText().toString();

        if(isEmpty(nombre)|| isEmpty(apellidos) || isEmpty(edad) || isEmpty(user) || isEmpty(pass))
        {
            Toast.makeText(this,R.string.fill, Toast.LENGTH_SHORT).show();
        }
        else {
            System.out.println(picture);
            mandarDatos();
        }
    }

    public void mandarDatos(){

        new Communication<Integer>(this) {
            @Override
            protected Integer work() throws Exception {

                ObtenerDatos data = new ObtenerDatos();
                respuesta = data.postUser(name,surname,age,picture,hobbies,username,password);

                if(respuesta.equals("Usuario añadido")){
                    return 200;
                }
                else if(respuesta.equals("Nombre de usuario existente. No se añadió a la base de datos")){
                    return 250;
                }
                else{
                    return 500;
                }
            }

            @Override
            protected void onFinish(Integer result) {
                System.out.println(result);
                if(result==200){
                    Intent intent = new Intent(NewAccount.this, Login.class);
                    Toast.makeText(NewAccount.this,respuesta,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else if(result==250){
                    Intent intent = new Intent(NewAccount.this, Login.class);
                    Toast.makeText(NewAccount.this,respuesta,Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(NewAccount.this,"Error al crear al usuario",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewAccount.this, Login.class);
                    startActivity(intent);
                }

            }
        }.execute();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if( resultCode != Activity.RESULT_OK)
            return;
        switch(requestCode){
            case PICTURE_REQUEST_CODE:
                Toast.makeText(this,picture,Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
