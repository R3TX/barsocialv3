package gr7.compumovil.udea.edu.co.barsocial3;


import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.Map;

/**
 * Created by retx_000 on 16/10/2016.
 */

public class OtherActivity extends AppCompatActivity {
    public final String TAG="OtherActivity.class";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**Pensar seriamente si permitir el drawlabe aca**/
        //Map<String, Object> message = (Map<String, Object>) getIntent().getSerializableExtra("datos");

        Fragment fragmentoGenerico = new FragmentoLugar();

        fragmentoGenerico.setArguments(getIntent().getBundleExtra("datos"));
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_main, fragmentoGenerico)
                .commit();

    }
}
