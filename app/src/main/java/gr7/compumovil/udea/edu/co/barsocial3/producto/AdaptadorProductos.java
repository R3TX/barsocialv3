package gr7.compumovil.udea.edu.co.barsocial3.producto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import gr7.compumovil.udea.edu.co.barsocial3.R;
import gr7.compumovil.udea.edu.co.barsocial3.producto.Comida;

/**
 * Created by r3tx on 4/10/16.
 */
public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> {
    String busqueda;

    public AdaptadorProductos(String busqueda) {
        this.busqueda=busqueda;
    }

    @Override
    public int getItemCount() {
       return Comida.COMIDAS_POPULARES.size();
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
       Comida item = Comida.COMIDAS_POPULARES.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());
        viewHolder.pequeñaDescripcion.setText("Algunos ingredientes van listados aca");


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
