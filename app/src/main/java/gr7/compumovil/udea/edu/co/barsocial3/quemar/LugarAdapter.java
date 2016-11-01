package gr7.compumovil.udea.edu.co.barsocial3.quemar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import gr7.compumovil.udea.edu.co.barsocial3.R;

/**
 * Created by retx_000 on 15/10/2016.
 */

public class LugarAdapter extends RecyclerView.ViewHolder {

    View v;
    public TextView nombre;
    public TextView pequeñaDescripcion;
    public ImageView imagenLugarMiniatura,estrella1,estrella2,estrella3,estrella4,estrella5;
    StorageReference storageReference ;

    public LugarAdapter(View itemView) {
        super(itemView);
    }

    public void setName(String nombre ){
        this.nombre = (TextView) v.findViewById(R.id.lista_lugar_nombre);
        this.nombre.setText(nombre);
    }
    public void setLitleDescription(String lDescrption){
        pequeñaDescripcion = (TextView) v.findViewById(R.id.lista_lugar_pequeña_descripcion);
        pequeñaDescripcion.setText(lDescrption);
    }
    public void setImagenLugarMiniatura(String image){
        storageReference = FirebaseStorage.getInstance().getReference(image);
        imagenLugarMiniatura = (ImageView) v.findViewById(R.id.lista_lugar_miniatura);
        Glide.with(itemView.getContext())
                .using(new FirebaseImageLoader())
                .load(storageReference)//"https://firebasestorage.googleapis.com/v0/b/barsocial-da3b2.appspot.com/o/cafe.jpg?alt=media&token=0f12fed5-e7d8-48d5-b3c4-dd6a5401a9e6")//imagen.get(i))
                .centerCrop()
                .into(imagenLugarMiniatura);
    }
    public void setStarts(double rate){

        estrella1 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_1);
        estrella2 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_2);
        estrella3 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_3);
        estrella4 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_4);
        estrella5 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_5);
        ImageView[] estrellas ={estrella1,estrella2,estrella3,estrella4,estrella5};
        String r = Double.toString(rate);
        String[] s=r.split("");
        int entero = Integer.parseInt(s[1]);
        int decimal = Integer.parseInt(s[3]);
        int i=0;
        while( i<=entero){
            if(entero==0){
                break;
            }
            estrellas[i].setImageResource(R.drawable.ic_star_full);
            i++;
        }
        if(entero==0 ){
            if(decimal<=5){
                estrellas[0].setImageResource(R.drawable.ic_star_half);
            }else{
                estrellas[0].setImageResource(R.drawable.ic_star_full);
            }
        }
        if((entero-1)<5){
            if(decimal<=5){
                estrellas[entero].setImageResource(R.drawable.ic_star_half);
            }else if(decimal>5){
                estrellas[entero].setImageResource(R.drawable.ic_star_full);
            }
        }


    }





}
