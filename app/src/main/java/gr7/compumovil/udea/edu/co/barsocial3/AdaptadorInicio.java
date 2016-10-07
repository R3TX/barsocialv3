package gr7.compumovil.udea.edu.co.barsocial3;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import gr7.compumovil.udea.edu.co.barsocial3.quemar.Comida;

/**
 * Created by r3tx on 4/10/16.
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {
    String busqueda;
    private DatabaseReference mDatabase;
    StorageReference storageRef;
    FirebaseStorage storage;

    public AdaptadorInicio(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://barsocial-da3b2.appspot.com/");
    }

    public AdaptadorInicio(String busqueda) {
        this.busqueda=busqueda;
    }

    @Override
    public int getItemCount() {
        return Comida.COMIDAS_POPULARES.size();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_lugares, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        //esto hay q modificarlo con la de lo nuestro
       Comida item = Comida.COMIDAS_POPULARES.get(i);


        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.pequeñaDescripcion.setText("$" + item.getPrecio());



    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView pequeñaDescripcion;
        public ImageView imagenLugarMiniatura,estrella1,estrella2,estrella3,estrella4,estrella5;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.lista_lugar_nombre);
            pequeñaDescripcion = (TextView) v.findViewById(R.id.lista_lugar_pequeña_descripcion);
            imagenLugarMiniatura = (ImageView) v.findViewById(R.id.lista_lugar_miniatura);
            estrella1 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_1);
            estrella2 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_2);
            estrella3 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_3);
            estrella4 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_4);
            estrella5 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_5);
        }
    }
}
