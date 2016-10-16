package gr7.compumovil.udea.edu.co.barsocial3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InicioFragment extends Fragment {
    public final String TAG = "inicio fragment";
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    private RecyclerView.Adapter adaptad;
    //ObtenerHelper obtenerHelper;
    private String busqueda;

    public InicioFragment(){
        //obtenerHelper = new ObtenerHelper();
    }

    public InicioFragment(String busqueda) {
        this.busqueda=busqueda;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        DatabaseReference ref =FirebaseDatabase.getInstance().getReference().child("lugares");;

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        adaptador = new AdaptadorInicio(busqueda);//obtenerHelper.getLugar());
        //Log.e(TAG, "lugares: "+ obtenerHelper.getLugar().size());
        //adaptador.notifyDataSetChanged();


        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
        return view;
    }
}
