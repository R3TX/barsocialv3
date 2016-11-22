package gr7.compumovil.udea.edu.co.barsocial3.lugar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gr7.compumovil.udea.edu.co.barsocial3.Decoracion.DecoracionLineaDivisoria;
import gr7.compumovil.udea.edu.co.barsocial3.R;
import gr7.compumovil.udea.edu.co.barsocial3.lugar.AdaptadorLugares;


public class MainLugarFragment extends Fragment {
    public final String TAG = "inicio fragment";
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorLugares adaptador;
    //buscar obtenerLugares;
    private String busqueda;

    public MainLugarFragment(){
        //obtenerLugares = new buscar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        adaptador = new AdaptadorLugares(getArguments());
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }



}
