package gr7.compumovil.udea.edu.co.barsocial3.producto;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gr7.compumovil.udea.edu.co.barsocial3.R;

/**
 * Created by r3tx on 4/10/16.
 */
public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> implements Observer{
    ObtenerProductos obtenerProductos;
    ArrayList productos;

    public AdaptadorProductos(Bundle bundle) {
        obtenerProductos = new ObtenerProductos(bundle);
        obtenerProductos.addObserver(this);
        productos = obtenerProductos.getProductos();
    }

    @Override
    public int getItemCount() {
       return productos.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragmento_lugar_info_productos_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        //esto hay q modificarlo con la de lo nuestro
        Map<String,Object> item = (Map<String, Object>) productos.get(i);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(item.get("imagenUrl").toString());

        Glide.with(viewHolder.itemView.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)//"https://firebasestorage.googleapis.com/v0/b/barsocial-da3b2.appspot.com/o/cafe.jpg?alt=media&token=0f12fed5-e7d8-48d5-b3c4-dd6a5401a9e6")//imagen.get(i))
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
        viewHolder.nombre.setText(item.get("name").toString());
        viewHolder.precio.setText("$" + item.get("precio").toString());
        viewHolder.pequeñaDescripcion.setText(item.get("ingredientes").toString());


    }

    @Override
    public void update(Observable observable, Object o) {
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView pequeñaDescripcion, precio;
        public ImageView imagenLugarMiniatura,estrella1,estrella2,estrella3,estrella4,estrella5;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            pequeñaDescripcion = (TextView) v.findViewById(R.id.ingrediente_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagenLugarMiniatura = (ImageView) v.findViewById(R.id.miniatura_comida);

        }
    }
}
