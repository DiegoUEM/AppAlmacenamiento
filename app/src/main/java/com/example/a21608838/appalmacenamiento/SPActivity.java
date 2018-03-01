package com.example.a21608838.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SPActivity extends AppCompatActivity {


    private EditText etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);

        etNombre = findViewById(R.id.etNombre);
    }
    //Metodo para almacenar datos compartidos(preferencia compartidas)
    public void GuardarSP (View v){
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        editor.putString("NOMBRE",etNombre.getText().toString());
        editor.commit();
        setResult(RESULT_OK, getIntent());
        finish();

    }
}
