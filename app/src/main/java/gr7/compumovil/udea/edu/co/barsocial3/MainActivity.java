package gr7.compumovil.udea.edu.co.barsocial3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import gr7.compumovil.udea.edu.co.barsocial3.evento.EventosFragmento;
import gr7.compumovil.udea.edu.co.barsocial3.lugar.MainLugarFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public final String TAG="MainActivity.class";
    ProgressBar pb = null;

    private DrawerLayout drawer;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Toast.makeText(this,"Existo",Toast.LENGTH_LONG);
        } else {
            Toast.makeText(this,"No_Existo",Toast.LENGTH_LONG);
        }

        auth.signInAnonymously();




        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        if (navigationView != null) {
            navigationView.setCheckedItem(0);
            onNavigationItemSelected(navigationView.getMenu().getItem(0));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        int id = item.getItemId();
        Bundle info = new Bundle();



        if (id == R.id.Bares) {
            info.putString("lugar", "Bar");
            info.putBoolean("evento", false);
            fragmentoGenerico = new MainLugarFragment();

            // Handle the camera action
        } else if (id == R.id.Cafe) {
            info.putString("lugar", "Cafe");
            info.putBoolean("evento", false);
            fragmentoGenerico = new MainLugarFragment();

        } else if (id == R.id.Restaurantes) {
            info.putString("lugar", "Restaurante");
            info.putBoolean("evento", false);
            fragmentoGenerico = new MainLugarFragment();

        } else if (id == R.id.Evento) {
            info.putString("lugar", "eventos");
            info.putBoolean("evento", true);
            fragmentoGenerico = new EventosFragmento();
            //fragmentoGenerico = new FragmentoLugar();

        }

        if (fragmentoGenerico != null) {
            fragmentoGenerico.setArguments(info);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, fragmentoGenerico)
                    .commit();
        }


        pb = (ProgressBar) findViewById(R.id.progress_bar);
        pb.setVisibility(View.VISIBLE);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
