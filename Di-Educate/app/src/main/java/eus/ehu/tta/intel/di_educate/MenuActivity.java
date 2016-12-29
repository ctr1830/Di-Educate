package eus.ehu.tta.intel.di_educate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_LOGIN= "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}
