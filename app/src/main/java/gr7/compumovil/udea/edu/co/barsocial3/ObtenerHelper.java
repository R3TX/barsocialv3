package gr7.compumovil.udea.edu.co.barsocial3;

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
public class ObtenerHelper extends Observable implements ValueEventListener {
    public final String TAG="ObtenerHelper";
    Map<String, Object> message;
    ArrayList lugar;
    private DatabaseReference mDatabase;
    String busqueda;


    public ObtenerHelper(String Busqueda){


        lugar = new ArrayList();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("lugares");
        /**implemnetar el query por el sevidor**/
        Query bar =  mDatabase.orderByChild("categoria");


        this.busqueda = Busqueda;
        bar.addValueEventListener(this);
    }



    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
                message =(Map<String, Object>)eventSnapshot.getValue();
                lugar.add(message);
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
