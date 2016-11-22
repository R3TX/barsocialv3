package gr7.compumovil.udea.edu.co.barsocial3.evento;

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
public class ObtenerEventos extends Observable implements ValueEventListener {
    public final String TAG="ObtenerEventos";
    Map<String, Object> message;
    ArrayList evento;
    private DatabaseReference mDatabase;

    static ObtenerEventos obtenerEventos;

    public static ObtenerEventos ObtenerEventos(){
        if (obtenerEventos == null){
            obtenerEventos = new ObtenerEventos();
        }
        return obtenerEventos;
    }

    private ObtenerEventos(){

    }

    public void Eventos(Bundle bundle){

        evento = new ArrayList();
        /**implemnetar el query por el sevidor**/
        generarQuery();

    }

    public void generarQuery(){
            mDatabase = FirebaseDatabase.getInstance().getReference().child("eventos");
            Query bar = mDatabase.orderByChild("name");
            bar.addValueEventListener(this);


    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){

                message = (Map<String, Object>) eventSnapshot.getValue();
                evento.add(message);

        }
        setChanged();
        notifyObservers();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    public ArrayList getEvento() {
        return evento;
    }

    public int cantidad(){return evento.size();}
}
