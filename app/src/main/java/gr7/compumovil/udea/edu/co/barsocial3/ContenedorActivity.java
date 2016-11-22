package gr7.compumovil.udea.edu.co.barsocial3;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Observable;
import java.util.Observer;

import gr7.compumovil.udea.edu.co.barsocial3.evento.FragmentoEvento;
import gr7.compumovil.udea.edu.co.barsocial3.evento.ObtenerEventos;
import gr7.compumovil.udea.edu.co.barsocial3.lugar.FragmentoLugar;
import gr7.compumovil.udea.edu.co.barsocial3.lugar.ObtenerLugares;

/**
 * Created by retx_000 on 16/10/2016.
 */

public class ContenedorActivity extends AppCompatActivity implements Observer{
    public final String TAG="ContenedorActivity.class";
    ProgressBar pb = null;
    ObtenerLugares obtenerLugares;
    ObtenerEventos obtenerEventos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        obtenerLugares = ObtenerLugares.ObtenerLugares();
        obtenerLugares.addObserver(this);

        obtenerEventos = ObtenerEventos.ObtenerEventos();
        obtenerEventos.addObserver(this);
        setContentView(R.layout.app_bar_main);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /**Pensar seriamente si permitir el drawlabe aca**/
        //Map<String, Object> message = (Map<String, Object>) getIntent().getSerializableExtra("datos");
        Bundle bundle =  getIntent().getBundleExtra("datos");
        Fragment fragmentoGenerico;
        boolean evento =bundle.getBoolean("evento");
        if(evento){
            fragmentoGenerico= new FragmentoEvento();
        }else{
            fragmentoGenerico= new FragmentoLugar();
        }


        fragmentoGenerico.setArguments(bundle);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.content_main, fragmentoGenerico)
                .commit();

        pb = (ProgressBar) findViewById(R.id.progress_bar);
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void update(Observable o, Object arg) {
        pb = (ProgressBar) findViewById(R.id.progress_bar);
        pb.setVisibility(View.GONE);
    }
}
