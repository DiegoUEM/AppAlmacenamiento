package com.example.a21608838.appalmacenamiento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String nombre;
    private TextView tvBienvenida;
    //Creamos una constante para utilizar en el onResult
    static final int SP_CODE=1;
    static final int AI_CODE=2;
    static final int AE_CODE=3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBienvenida = findViewById(R.id.tvBienvenida);
        //Ahora vamos a preguntar pòr la clave dentro del SP
        cargarPreferencias();

    }
    //Solo utilizaremos este metodo para pasar al siguiente activiy
    public void almacenarSP (View v){
        Intent i = new Intent (this, SPActivity.class);
        startActivityForResult(i,SP_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == SP_CODE) {
            if (requestCode == RESULT_OK) {
                cargarPreferencias();

            }
        } else if (requestCode == AI_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Se ha realizado el guardado de informacion", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No se ha podido realizar el almacenamiento interno", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == AE_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Se ha realizado el guardado externo de informacion", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "No se ha podido realizar el almacenamiento externo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void cargarPreferencias() {
        //Ahora vamos a preguntar pòr la clave dentro del SP
        SharedPreferences sp = getSharedPreferences("SPActivity", MODE_PRIVATE);
        //primer parametro la clave, segundo parametro valor por defecto
        nombre = sp.getString("NOMBRE", "Anonimo");

        tvBienvenida.setText("Bienvenido " + nombre);
    }

    //Solo utilizaremos este metodo para pasar al siguiente activiy
    public void almacenarAI (View v){
        Intent i = new Intent (this, AInternoActivity.class);
        startActivityForResult(i,AI_CODE);
    }
    public void almacenarAEx (View v){
        Intent i = new Intent (this, AExternoActivity.class);
        startActivityForResult(i,AE_CODE);
    }
    public void consultarAI(View v){
        Intent i = new Intent(this, ConsultaAIActivity.class);
        startActivity(i);
    }
    public void consultarAE(View v){
        Intent i = new Intent(this, ConsultaAEActivity.class);
        startActivity(i);
    }

}
