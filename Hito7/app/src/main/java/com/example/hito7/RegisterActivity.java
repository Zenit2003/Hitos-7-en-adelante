package com.example.hito7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private String TAG = "Hola";
    private EditText register_et_correo;
    private EditText register_et_password;
    private Button register_btn_register;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDB = FirebaseDatabase.getInstance();
    private DatabaseReference mDataRef = mDB.getReference();
    private LeerEscribir database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = new LeerEscribir(mDataRef);
        register_et_correo = findViewById(R.id.register_et_correo);
        register_et_password = findViewById(R.id.register_et_password);
        register_btn_register = findViewById(R.id.register_btn_register);

        mAuth = FirebaseAuth.getInstance();
        mDataRef = mDB.getReference();

        register_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (register_et_password != null && register_et_correo != null){
                    String email;
                    String password;

                    email = register_et_correo.getText().toString();
                    password = register_et_password.getText().toString();

                    registrarCuenta(email, password);
                }
            }
        });


    }

    public void registrarCuenta(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(RegisterActivity.this, BienvenidoActivity.class));
                            database.escribirUsuario(email, password);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}