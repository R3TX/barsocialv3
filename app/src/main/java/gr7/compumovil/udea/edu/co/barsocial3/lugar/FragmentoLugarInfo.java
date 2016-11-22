package gr7.compumovil.udea.edu.co.barsocial3.lugar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.plus.model.people.Person;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

import gr7.compumovil.udea.edu.co.barsocial3.MapsActivity;
import gr7.compumovil.udea.edu.co.barsocial3.R;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static java.security.AccessController.getContext;


/**
 *Fragmento que contendra la info del lugar, como foto, Descripcion, direccion, gps, calificacion y comentarios
 */
public class FragmentoLugarInfo extends Fragment {
    ImageView imageView, estrella1, estrella2, estrella3, estrella4, estrella5;
    TextView name,
    descripcion;
    Map<String, Object> datos;
    Button btn;
    String cLon;
    String cLat;



    public FragmentoLugarInfo() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle b = getArguments();

        datos = (Map<String, Object>) b.getSerializable("datos");
        View view = inflater.inflate(R.layout.fragment_fragmento_lugar_info, container, false);
        //Toast.makeText(view.getContext(), datos.get("imagenUrl").toString(), Toast.LENGTH_LONG).show();
        btn = (Button) view.findViewById(R.id.btn);
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


        estrella1 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_1);
        estrella2 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_2);
        estrella3 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_3);
        estrella4 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_4);
        estrella5 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_5);
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
        setStarts(Double.valueOf(datos.get("rate").toString()));

    }

    public void setStarts(double rate){

        ImageView[] estrellas ={estrella1,estrella2,estrella3,estrella4,estrella5};
        for(int u = 0; u<5;u++){
            estrellas[u].setImageResource(R.drawable.ic_star_empty);
        }
        String r = Double.toString(rate);
        String[] s=r.split("");
        int entero = Integer.parseInt(s[1]);

        int decimal = Integer.parseInt(s[3]);

        int i=0;
        while( i<entero){

            estrellas[i].setImageResource(R.drawable.ic_star_full);
            i++;
        }
        if(entero==0 ){
            if(decimal>0){
                estrellas[0].setImageResource(R.drawable.ic_star_half);
            }else if(decimal==0){
                estrellas[0].setImageResource(R.drawable.ic_star_empty);
            }
        }
        if(entero==5){
            if(decimal>0){
                estrellas[entero-1].setImageResource(R.drawable.ic_star_half);
            }else if(decimal==0){
                estrellas[entero-1].setImageResource(R.drawable.ic_star_full);
            }
        }
        if(entero!=5 && entero !=0){
            if(decimal>0){
                estrellas[entero].setImageResource(R.drawable.ic_star_half);
            }
        }


    }


  //  public void abrirMapa(View view){

    //    Toast.makeText(getContext(),"Hola", Toast.LENGTH_SHORT).show();

    //}


}
