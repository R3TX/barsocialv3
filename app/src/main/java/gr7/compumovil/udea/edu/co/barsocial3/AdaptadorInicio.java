package gr7.compumovil.udea.edu.co.barsocial3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gr7.compumovil.udea.edu.co.barsocial3.DAO.Lugar;

/**
 * Created by r3tx on 4/10/16.
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {

    public AdaptadorInicio() {
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
        Lugar item = Comida.COMIDAS_POPULARES.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());

    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagenLugarMiniatura,estrella1,estrella2,estrella3,estrella4,estrella5;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.lista_lugar_nombre);
            precio = (TextView) v.findViewById(R.id.lista_lugar_pequeña_descripcion);
            imagenLugarMiniatura = (ImageView) v.findViewById(R.id.lista_lugar_miniatura);
            estrella1 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_1);
            estrella2 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_2);
            estrella3 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_3);
            estrella4 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_4);
            estrella5 = (ImageView) v.findViewById(R.id.lista_lugar_estrella_5);
        }
    }
}
