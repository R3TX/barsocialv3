package gr7.compumovil.udea.edu.co.barsocial3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 *Fragmento que contendra la info del lugar, como foto, Descripcion, direccion, gps, calificacion y comentarios
 */
public class FragmentoLugarInfo extends Fragment {
    public FragmentoLugarInfo() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragmento_lugar_info, container, false);
    }
}
