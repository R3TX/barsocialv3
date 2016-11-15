package gr7.compumovil.udea.edu.co.barsocial3.lugar;

import android.os.Bundle;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import gr7.compumovil.udea.edu.co.barsocial3.DAO.Lugar;

/**
 * Created by r3tx on 10/10/16.
 */
public class ObtenerLugares extends Observable implements ValueEventListener {
    public final String TAG="ObtenerLugares";
    Map<String, Object> message, ensayoBusqueda;
    ArrayList lugar;
    private DatabaseReference mDatabase;
    String busqueda;
    boolean evento;
    Bundle bundle;


    public ObtenerLugares(Bundle bundle){


        evento = bundle.getBoolean("evento");
        if(!evento) {
            busqueda = bundle.getString("lugar");
        }else{
            Map<String, Object>ensayoBusqueda2 = (Map<String, Object>) bundle.getSerializable("datos");
            ensayoBusqueda = (Map<String, Object>) ensayoBusqueda2.get("lugar");
        }
        lugar = new ArrayList();
        /**implemnetar el query por el sevidor**/
        generarQuery();

    }

    public void generarQuery(){

            mDatabase = FirebaseDatabase.getInstance().getReference().child("lugares");
            Query bar = mDatabase.orderByChild("categoria");
            bar.addValueEventListener(this);


    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
            if(!evento) {
                if (eventSnapshot.child("categoria").hasChild(busqueda)) {
                    message = (Map<String, Object>) eventSnapshot.getValue();
                    lugar.add(message);
                }
            }else{
                String key = eventSnapshot.getKey();
                Log.e(TAG,key);
                if(ensayoBusqueda.containsKey(key)) {
                    message = (Map<String, Object>) eventSnapshot.getValue();
                    lugar.add(message);
                }
            }

        }
        setChanged();
        notifyObservers();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    public ArrayList getLugar() {
        return lugar;
    }
    public int cantidad(){return lugar.size();}
}
