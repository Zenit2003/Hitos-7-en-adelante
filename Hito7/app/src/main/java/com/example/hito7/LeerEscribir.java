package com.example.hito7;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LeerEscribir {

    private static final String TAG = "ReadAndWriteSnippets";
    private DatabaseReference mDatabase;


    //Constructor
    public LeerEscribir(DatabaseReference database) {
        mDatabase = FirebaseDatabase.getInstance("https://hito7estees07-02-default-rtdb.europe-west1.firebasedatabase.app/").getReference();

    }

    public void escribirUsuario(String email, String password) {
        Usuario usuario = new Usuario(email, password);
        mDatabase.child("users").child(email).setValue(usuario);
    }

    public void borrar() {
        mDatabase.child("users").setValue(null);


    }
}
