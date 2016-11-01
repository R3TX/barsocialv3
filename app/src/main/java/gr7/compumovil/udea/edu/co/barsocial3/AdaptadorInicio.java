package gr7.compumovil.udea.edu.co.barsocial3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gr7.compumovil.udea.edu.co.barsocial3.DAO.Lugar;
import gr7.compumovil.udea.edu.co.barsocial3.quemar.Comida;

import static java.lang.Integer.compare;

/**
 * Created by r3tx on 4/10/16.
 */
class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> implements Observer {
    public final static String TAG = "Adaptador Inicio";

    ObtenerHelper obtenerHelper;
    static ArrayList lugar,imagen;
    public AdaptadorInicio(){//ArrayList obtener){
        obtenerHelper = new ObtenerHelper("Bar");
        obtenerHelper.addObserver(this);
        lugar=obtenerHelper.getLugar();
        //imagen=obtenerHelper.getImagen();



    }

    public AdaptadorInicio(String busqueda){//ArrayList obtener){
        obtenerHelper = new ObtenerHelper(busqueda);
        obtenerHelper.addObserver(this);
        lugar=obtenerHelper.getLugar();
        //imagen=obtenerHelper.getImagen();



    }






    @Override
    public int getItemCount() {
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
        //esto hay q modificarlo con la de lo nuestro
       //Comida item = Comida.COMIDAS_POPULARES.get(i);
       // Lugar item = (Lugar) message.get("lugar");
//        Log.e(TAG,imagen.get(i).toString());
        Map<String,Object> item = (Map<String, Object>) lugar.get(i);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(item.get("imagenUrl").toString());
        Glide.with(viewHolder.itemView.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)//"https://firebasestorage.googleapis.com/v0/b/barsocial-da3b2.appspot.com/o/cafe.jpg?alt=media&token=0f12fed5-e7d8-48d5-b3c4-dd6a5401a9e6")//imagen.get(i))
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
        viewHolder.nombre.setText(item.get("name").toString());
        viewHolder.pequeñaDescripcion.setText( item.get("pequeñaDescripcion").toString());
        viewHolder.setStarts(Double.valueOf(item.get("rate").toString()));


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
        public ImageView imagenLugarMiniatura,estrella1,estrella2,estrella3,estrella4,estrella5;

        public ViewHolder(View v) {
            super(v);
            context=v.getContext();
            nombre = (TextView) v.findViewById(R.id.lista_lugar_nombre);
            pequeñaDescripcion = (TextView) v.findViewById(R.id.lista_lugar_pequeña_descripcion);
            imagenLugarMiniatura = (ImageView) v.findViewById(R.id.lista_lugar_miniatura);
            estrella1 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_1);
            estrella2 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_2);
            estrella3 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_3);
            estrella4 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_4);
            estrella5 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_5);
            v.setClickable(true);
            v.setOnClickListener(this);
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

        @Override
        public void onClick(View view) {
           // Bundle b = new Bundle();
            //b.putParcelable("datos", (Parcelable) lugar.get(getPosition()));
            Intent i = new Intent(context, OtherActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle b = new Bundle();
            b.putSerializable("datos",(Serializable) lugar.get(getPosition()) );
            i.putExtra("datos",b);
            context.getApplicationContext().startActivity(i);
        }
    }

}
