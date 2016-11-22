package gr7.compumovil.udea.edu.co.barsocial3.evento;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

import gr7.compumovil.udea.edu.co.barsocial3.MapsActivity;
import gr7.compumovil.udea.edu.co.barsocial3.R;


/**
 *Fragmento que contendra la info del lugar, como foto, Descripcion, direccion, gps, calificacion y comentarios
 */
public class FragmentoEventoInfo extends Fragment {
    ImageView imageView;
    TextView name,
    descripcion;
    Map<String, Object> datos;
    Button btn;
    String cLat;
    String cLon;



    public FragmentoEventoInfo() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        datos = (Map<String, Object>) bundle.getSerializable("datos");
        View view = inflater.inflate(R.layout.fragment_fragmento_evento_info, container, false);
        //Toast.makeText(view.getContext(), datos.get("imagenUrl").toString(), Toast.LENGTH_LONG).show();
        btn = (Button) view.findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MapsActivity.class);

                Map<String, Object> c = (Map<String, Object>) datos.get("gps");
                cLat = c.get("lat").toString();
                cLon = c.get("lon").toString();
                intent.putExtra("lat", cLat);
                intent.putExtra("lon", cLon);

                startActivity(intent);


                //Toast.makeText(getContext(),c.get("lon").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        crear(view);

        return view;
    }

    public void crear(View v){
        imageView = (ImageView) v.findViewById(R.id.imageView_lugar_info_imagen) ;
        name = (TextView) v.findViewById(R.id.textView_lugar_info_nombre_lugar);
        descripcion = (TextView) v.findViewById(R.id.textView_lugar_info_descripcion_lugar);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(datos.get("imagenUrl").toString());
        Glide.with(v.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)//"https://firebasestorage.googleapis.com/v0/b/barsocial-da3b2.appspot.com/o/cafe.jpg?alt=media&token=0f12fed5-e7d8-48d5-b3c4-dd6a5401a9e6")//imagen.get(i))
                .centerCrop()
                .into(imageView);

        name.setText(datos.get("name").toString());
        descripcion.setText(datos.get("descripcion").toString());

    }

}
