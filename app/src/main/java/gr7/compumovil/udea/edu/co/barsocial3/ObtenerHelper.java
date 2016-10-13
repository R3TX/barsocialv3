package gr7.compumovil.udea.edu.co.barsocial3;

import android.support.annotation.NonNull;

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

/**
 * Created by r3tx on 10/10/16.
 */
public class ObtenerHelper implements ValueEventListener,OnSuccessListener<byte[]>,OnFailureListener{
    Map<String, Object> message;
    ArrayList lugar,imagen;
    private StorageReference storageRef;
    private FirebaseStorage storage;
    private DatabaseReference mDatabase;
    //private byte[] imagen;

    public ObtenerHelper(){
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

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        System.out.println("Error "+dataSnapshot.exists());
        System.out.println("hay "+ dataSnapshot.getChildrenCount());
        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()){
            if(eventSnapshot.child("categoria").hasChild("Bar")){
                message =(Map<String, Object>)eventSnapshot.getValue();
                lugar.add(message);
                System.out.print((String) message.get("imagenUrl"));
                storageRef.child((String) message.get("imagenUrl")).getBytes(Long.MAX_VALUE).addOnSuccessListener(this).addOnFailureListener(this);
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