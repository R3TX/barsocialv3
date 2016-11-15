package gr7.compumovil.udea.edu.co.barsocial3.evento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gr7.compumovil.udea.edu.co.barsocial3.ContenedorActivity;
import gr7.compumovil.udea.edu.co.barsocial3.R;

/**
 * Created by r3tx on 4/10/16.
 */
class AdaptadorEventos extends RecyclerView.Adapter<AdaptadorEventos.ViewHolder> implements Observer {
    public final static String TAG = "AdaptadorEventos";

    ObtenerEventos obtenerEventos;
    static ArrayList evento;

    public AdaptadorEventos(Bundle bundle){
        obtenerEventos = new ObtenerEventos(bundle);
        obtenerEventos.addObserver(this);
        evento = obtenerEventos.getEvento();
    }






    @Override
    public int getItemCount() {
        return evento.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_eventos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Map<String,Object> item = (Map<String, Object>) evento.get(i);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(item.get("imagenUrl").toString());
        Glide.with(viewHolder.itemView.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)//"https://firebasestorage.googleapis.com/v0/b/barsocial-da3b2.appspot.com/o/cafe.jpg?alt=media&token=0f12fed5-e7d8-48d5-b3c4-dd6a5401a9e6")//imagen.get(i))
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
        viewHolder.nombre.setText(item.get("name").toString());
        viewHolder.pequeñaDescripcion.setText( item.get("descripcion").toString());



    }

    @Override
    public void update(Observable o, Object arg) {
        notifyDataSetChanged();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
        // Campos respectivos de un item
        public Context context;
        public TextView nombre;
        public TextView pequeñaDescripcion;
        public ImageView imagenLugarMiniatura;

        public ViewHolder(View v) {
            super(v);
            context=v.getContext();
            nombre = (TextView) v.findViewById(R.id.lista_lugar_nombre);
            pequeñaDescripcion = (TextView) v.findViewById(R.id.lista_lugar_pequeña_descripcion);
            imagenLugarMiniatura = (ImageView) v.findViewById(R.id.lista_lugar_miniatura);
            v.setClickable(true);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, ContenedorActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle = new Bundle();
            bundle.putSerializable("datos",(Serializable) evento.get(getPosition()) );
            bundle.putBoolean("evento",true);
            i.putExtra("datos",bundle);
            context.getApplicationContext().startActivity(i);
        }
    }

}
