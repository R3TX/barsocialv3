package gr7.compumovil.udea.edu.co.barsocial3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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

import gr7.compumovil.udea.edu.co.barsocial3.quemar.Comida;

/**
 * Created by r3tx on 4/10/16.
 */
public class AdaptadorInicio extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> implements ValueEventListener,OnSuccessListener<byte[]>,OnFailureListener {
   /* String busqueda;
    ArrayList lugares, imagenes;
    ObtenerHelper obtenerHelper;
*/



    public AdaptadorInicio() throws InterruptedException {
       /* obtenerHelper = new ObtenerHelper();
        lugares = obtenerHelper.getLugar();
        imagenes=obtenerHelper.getImagen();
        getItemCount();

*/
        System.out.println("p Entro al constructor");
        ObtenerHelper();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("p salgo del constructor");
    }






    @Override
    public int getItemCount() {
        //System.out.println("\n\n\n estoy rodeado  "+ obtenerHelper.cantidad()+"\n\n\n\n\n");
        System.out.println("p pido el tamaño");

        return lugar.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        System.out.println("p creo el viewHolder");

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


/*
        Glide.with(viewHolder.itemView.getContext())
                .load(imagen.get(i))
                .centerCrop()
                .into(viewHolder.imagenLugarMiniatura);
*/
        viewHolder.nombre.setText(item.get("name").toString());
        viewHolder.pequeñaDescripcion.setText( item.get("pequeñaDescripcion").toString());



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



    Map<String, Object> message;
    ArrayList lugar,imagen;
    private StorageReference storageRef;
    private FirebaseStorage storage;
    private DatabaseReference mDatabase;
    //private byte[] imagen;

    public void ObtenerHelper(){
        lugar = new ArrayList();
        imagen = new ArrayList();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("lugares");
        Query bar =  mDatabase.orderByChild("categoria");



        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://barsocial-da3b2.appspot.com/");

        bar.addValueEventListener(this);
    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(byte[] bytes) {
        imagen.add(bytes);
        System.out.println("Imagenes  "+ imagen.size());

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
            if(eventSnapshot.child("categoria").hasChild("Restaurante")){
                message =(Map<String, Object>)eventSnapshot.getValue();
                lugar.add(message);
                //storageRef.child((String) message.get("imagenUrl")).getBytes(Long.MAX_VALUE).addOnSuccessListener(this).addOnFailureListener(this);
            }
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public ArrayList getImagen() {
        return imagen;
    }

    public ArrayList getLugar() {
        return lugar;
    }
    public int cantidad(){return lugar.size();}
}
