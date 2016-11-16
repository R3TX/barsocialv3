package gr7.compumovil.udea.edu.co.barsocial3.producto;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

/**
 * Created by r3tx on 10/10/16.
 */
public class ObtenerProductos extends Observable implements ValueEventListener {
    public final String TAG="ObtenerProducto";
    Map<String, Object> message, ensayoBusqueda;
    ArrayList productos;
    private DatabaseReference mDatabase;



    public ObtenerProductos(Bundle bundle){


        Map<String, Object>ensayoBusqueda2 = (Map<String, Object>) bundle.getSerializable("datos");
        ensayoBusqueda = (Map<String, Object>) ensayoBusqueda2.get("productos");
        productos = new ArrayList();
        /**implemnetar el query por el sevidor**/
        generarQuery();

    }

    public void generarQuery(){
            mDatabase = FirebaseDatabase.getInstance().getReference().child("productos");
            Query bar = mDatabase.orderByChild("name");
            bar.addValueEventListener(this);


    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
            String key = eventSnapshot.getKey();//obtenemos la clave padre de este dato
            if(ensayoBusqueda.containsKey(key)) {
                message = (Map<String, Object>) eventSnapshot.getValue();
                productos.add(message);
            }
        }
        setChanged();
        notifyObservers();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    public ArrayList getProductos() {
        return productos;
    }
    public int cantidad(){return productos.size();}
}
