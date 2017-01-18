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

public class NewAccount extends AppCompatActivity {
    public final static int PICTURE_REQUEST_CODE=1;
    public final static int READ_REQUEST_CODE=2;

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
                    File file = File.createTempFile("tta", ".jpg", dir);
                    Uri pictureUri = Uri.fromFile(file);
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

        //Mandar al servidor los datos
        EditText name=(EditText)findViewById(R.id.nombre);
        EditText apellidos=(EditText)findViewById(R.id.apellidos);
        EditText edad=(EditText)findViewById(R.id.edad);
        EditText username=(EditText)findViewById(R.id.nombre_usuario);
        EditText password=(EditText)findViewById(R.id.password);



        if(isEmpty(name)|| isEmpty(apellidos) || isEmpty(edad) || isEmpty(username) || isEmpty(password))
        {
            Toast.makeText(this,R.string.fill, Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if( resultCode != Activity.RESULT_OK)
            return;
        switch(requestCode){
            case PICTURE_REQUEST_CODE:
                Toast.makeText(this,"foto adquirida",Toast.LENGTH_SHORT).show();
                //subirFichero(data.getData());
                break;
            case READ_REQUEST_CODE:
                Uri uri=null;
                if(data != null){
                    uri=data.getData();
                    dumpMetaData(uri);
                }
                break;
        }

    }

    public void subirFichero(View v){

        int miversion = android.os.Build.VERSION.SDK_INT;
        if (miversion >= Build.VERSION_CODES.KITKAT) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent, READ_REQUEST_CODE);
        }
        else{
            Toast.makeText(this,R.string.no_api,Toast.LENGTH_SHORT).show();
        }
    }

    public void dumpMetaData(Uri uri){

        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            cursor =NewAccount.this.getContentResolver()
                    .query(uri, null, null, null, null, null);
        }
        else {
            Toast.makeText(this,R.string.no_api,Toast.LENGTH_SHORT).show();
        }
        try {
            if (cursor != null && cursor.moveToFirst()) {

                String displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                String size = null;
                if (!cursor.isNull(sizeIndex)) {

                    size = cursor.getString(sizeIndex);
                    Toast.makeText(this,displayName+" "+ size + " bytes",Toast.LENGTH_SHORT).show();
                } else {
                    size = "Unknown";
                    Toast.makeText(this,displayName,Toast.LENGTH_SHORT).show();
                }
            }
        } finally {
            cursor.close();
        }
    }
}
