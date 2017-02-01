package eus.ehu.tta.intel.di_educate.vista;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import eus.ehu.tta.intel.di_educate.R;

public class SubMenuActivity extends AppCompatActivity {

    private static String userid;
    private static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        Bundle extras=getIntent().getExtras();
        String opcion = extras.getString("opcion");
        username=extras.getString("username");
        userid=extras.getString("userid");

        System.out.println("USERID SUB: "+userid);
        System.out.println("USERNAME SUB: "+username);

         submenu(opcion);
    }

    public void submenu(String opcion){
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_sub_menu);

        switch(opcion){
            case "leoleo":
                layout.removeView(findViewById(R.id.escucho1));
                layout.removeView(findViewById(R.id.escucho2));
                layout.removeView(findViewById(R.id.escucho3));
                layout.removeView(findViewById(R.id.juego1));
                layout.removeView(findViewById(R.id.juego2));
                layout.removeView(findViewById(R.id.seguimiento2));
                layout.removeView(findViewById(R.id.seguimiento3));
                layout.setBackgroundResource(R.drawable.fondo2);
                setTitle("Leo Leo");

                MediaPlayer media_leoleo= MediaPlayer.create(this,R.raw.leoleo);
                media_leoleo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });
                media_leoleo.start();
                break;
            case "juego":
                layout.removeView(findViewById(R.id.leoleo1));
                layout.removeView(findViewById(R.id.leoleo2));
                layout.removeView(findViewById(R.id.leoleo3));
                layout.removeView(findViewById(R.id.escucho1));
                layout.removeView(findViewById(R.id.escucho2));
                layout.removeView(findViewById(R.id.escucho3));
                layout.removeView(findViewById(R.id.seguimiento1));
                layout.removeView(findViewById(R.id.seguimiento3));
                setTitle("Juego con las palabras");
                layout.setBackgroundResource(R.drawable.fondo3);

                MediaPlayer media_juego= MediaPlayer.create(this,R.raw.juego);
                media_juego.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });
                media_juego.start();
                break;
            case "escucho":
                layout.removeView(findViewById(R.id.leoleo1));
                layout.removeView(findViewById(R.id.leoleo2));
                layout.removeView(findViewById(R.id.leoleo3));
                layout.removeView(findViewById(R.id.juego1));
                layout.removeView(findViewById(R.id.juego2));
                layout.removeView(findViewById(R.id.seguimiento1));
                layout.removeView(findViewById(R.id.seguimiento2));
                setTitle("Escucho mi voz");
                layout.setBackgroundResource(R.drawable.fondo4);

                MediaPlayer media_escucho= MediaPlayer.create(this,R.raw.escucho);
                media_escucho.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });
                media_escucho.start();
                break;
        }
    }

    public void leoleo1(View v){
        Intent intent= new Intent(this,LeoLeo1.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void leoleo2(View v){
        Intent intent= new Intent(this,LeoLeo2.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void leoleo3(View v){
        Intent intent= new Intent(this,LeoLeo3.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void juego1(View v){
        Intent intent= new Intent(this,Juego1.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void juego2(View v){
        Intent intent= new Intent(this,Juego2.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void escucho1(View v){
        Intent intent= new Intent(this,Escucho1.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void escucho2(View v){
        Intent intent= new Intent(this,Escucho2.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }
    public void escucho3(View v){
        Intent intent= new Intent(this,Pre_Escucho3.class);
        Bundle extras=new Bundle();
        extras.putString("userid",userid);
        extras.putString("username",username);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void seguimiento1(View v){
        Intent intent= new Intent(this,Seguimiento.class);
        Bundle bundle=new Bundle();
        bundle.putString("opcion","leoleo");
        bundle.putString("username",username);
        bundle.putString("userid",userid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void seguimiento2(View v){
        Intent intent= new Intent(this,Seguimiento.class);
        Bundle bundle=new Bundle();
        bundle.putString("opcion","juego");
        bundle.putString("username",username);
        bundle.putString("userid",userid);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void seguimiento3(View v){
        Intent intent= new Intent(this,Seguimiento.class);
        Bundle bundle=new Bundle();
        bundle.putString("opcion","escucho");
        bundle.putString("username",username);
        bundle.putString("userid",userid);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
