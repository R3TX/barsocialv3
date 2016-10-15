package gr7.compumovil.udea.edu.co.barsocial3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gr7.compumovil.udea.edu.co.barsocial3.quemar.Comida;

/**
 * Created by r3tx on 4/10/16.
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> implements Observer {
    public final String TAG = "Adaptador Inicio";

    ObtenerHelper obtenerHelper;
    ArrayList lugar,imagen;
    public AdaptadorInicio(){//ArrayList obtener){
        obtenerHelper = new ObtenerHelper("Bar");
        obtenerHelper.addObserver(this);
        lugar=obtenerHelper.getLugar();
        imagen=obtenerHelper.getImagen();


    }

    public AdaptadorInicio(String busqueda){//ArrayList obtener){
        obtenerHelper = new ObtenerHelper(busqueda);
        obtenerHelper.addObserver(this);
        lugar=obtenerHelper.getLugar();


    }






    @Override
    public int getItemCount() {
        Log.e(TAG, "lugares: "+ lugar.size());
        return lugar.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_lugares, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        System.out.println("p creo el conenido del view holder");

        //esto hay q modificarlo con la de lo nuestro
       //Comida item = Comida.COMIDAS_POPULARES.get(i);
       // Lugar item = (Lugar) message.get("lugar");
        Map<String,Object> item = (Map<String, Object>) lugar.get(i);
        Glide.with(viewHolder.itemView.getContext())
                .load(imagen.get(i))
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
        viewHolder.nombre.setText(item.get("name").toString());
        viewHolder.pequeñaDescripcion.setText( item.get("pequeñaDescripcion").toString());



    }

    @Override
    public void update(Observable o, Object arg) {
        Log.e(TAG, "observe");

        notifyDataSetChanged();
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
