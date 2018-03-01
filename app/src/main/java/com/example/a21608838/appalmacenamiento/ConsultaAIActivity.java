package com.example.a21608838.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsultaAIActivity extends AppCompatActivity {
    private TextView tvTitulo;
    private TextView tvContenido;
    private String nombreFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ai);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvContenido = findViewById(R.id.tvContenido);

        recuperarNombreFichero();
        tvTitulo.setText(tvTitulo.getText().toString() + nombreFichero);
        cargarContenido();

    }

    private void cargarContenido() {
        FileInputStream fis  = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String linea = "";
        String texto = "";
        try {
            fis = openFileInput(nombreFichero);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            while((linea = br.readLine()) != null){
                texto += linea + "\n";
            }
            tvContenido.setText(texto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"El fichero no ha sido encontrado",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,"Error de conexion",Toast.LENGTH_LONG).show();
        } finally {
            try{
                if (br != null){
                  br.close();
                }
                if (isr != null){
                    isr.close();
                }
                if (fis != null){
                    fis.close();
                }
            } catch (IOException e){
                e.printStackTrace();
                Toast.makeText(this,"Error de conexion",Toast.LENGTH_LONG).show();
            }
        }



    }

    private void recuperarNombreFichero() {
        SharedPreferences sp = getSharedPreferences("SPActivity", MODE_PRIVATE);
        nombreFichero = sp.getString("FICHERO_INT", "Anonimo");



    }
}
