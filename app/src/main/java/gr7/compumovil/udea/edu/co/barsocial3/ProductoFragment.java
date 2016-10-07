package gr7.compumovil.udea.edu.co.barsocial3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**

 */
public class ProductoFragment extends Fragment {

    private LinearLayoutManager linearLayout;

    public ProductoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grup_recycler_productos_lugar, container, false);

        RecyclerView reciclador = (RecyclerView)view.findViewById(R.id.recyclerview_Lugar_info_reciclador_productos);
        linearLayout = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(linearLayout);

        AdaptadorProductos adaptador = new AdaptadorProductos("null");
        reciclador.setAdapter(adaptador);
        reciclador.addItemDecoration(new DecoracionLineaDivisoria(getActivity()));

        return view;
    }
}
