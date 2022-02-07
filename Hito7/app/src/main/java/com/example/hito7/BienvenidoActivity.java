package com.example.hito7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BienvenidoActivity extends AppCompatActivity {

    private TextView tv_bienvenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        tv_bienvenido = findViewById(R.id.tv_bienvenido);

        Toast.makeText(BienvenidoActivity.this, "Se ha registrado/ingresado el usuario satisfactoriamente", Toast.LENGTH_LONG).show();
    }
}