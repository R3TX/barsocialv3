package gr7.compumovil.udea.edu.co.barsocial3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by r3tx on 6/10/16.
 */
public class EventosFragmento extends Fragment{
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorEventos adaptador;
    private ProgressBar spinner;
   // ObtenerHelper obtenerHelper = new ObtenerHelper();

    /*
        public InicioFragment(String busqueda) {
            //this.busqueda=busqueda;
        }
    */
    public EventosFragmento(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("creo vista adaptador", "la puta la vista del adaptador");
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        //spinner=(ProgressBar) view.findViewById(R.id.progressBar);
        //spinner.setVisibility(View.VISIBLE);

            adaptador = new AdaptadorEventos(getArguments());//obtenerHelper.getLugar());
        adaptador.notifyDataSetChanged();


        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));
        return view;
    }
}
