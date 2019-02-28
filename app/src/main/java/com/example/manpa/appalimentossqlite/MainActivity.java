package com.example.manpa.appalimentossqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manpa.appalimentossqlite.db.AlimentosContact;
import com.example.manpa.appalimentossqlite.db.AlimentosDatabase;
import com.example.manpa.appalimentossqlite.model.Alimento;

public class MainActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etTipo;
    EditText etOrigen;
    EditText etNutrientes;
    EditText etFuncion;
    AlimentosDatabase ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = findViewById(R.id.etNombreM);
        etTipo = findViewById(R.id.etTipoM);
        etOrigen = findViewById(R.id.etOrigenM);
        etNutrientes = findViewById(R.id.etNutrientesM);
        etFuncion = findViewById(R.id.etFuncionM);

        ads = new AlimentosDatabase(this);
    }

    public void limpiar(View view) {
        etNombre.setText("");
        etTipo.setText("");
        etOrigen.setText("");
        etNutrientes.setText("");
        etFuncion.setText("");
    }

    public void registrar(View view) {
        String nombre = etNombre.getText().toString().trim();
        String tipo = etTipo.getText().toString().trim();
        String origen = etOrigen.getText().toString().trim();
        String nutrientes = etNutrientes.getText().toString().trim();
        String funciones = etFuncion.getText().toString().trim();

        if (nombre.isEmpty() || tipo.isEmpty() || origen.isEmpty() || nutrientes.isEmpty() || funciones.isEmpty()) {
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_LONG).show();
        } else {
            Alimento alimento = new Alimento(nombre, tipo, origen, nutrientes, funciones);

            long id = ads.insertarAlimento(alimento);
            if (id != -1) {

                Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_LONG).show();
                alimento.setId((int) id);

                etNombre.setText("");
                etTipo.setText("");
                etOrigen.setText("");
                etNutrientes.setText("");
                etFuncion.setText("");
            }else{
                Toast.makeText(this, "No se ha podido guardar", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void consultarAlimento(View view){
        String nombre = etNombre.getText().toString().trim();


        if (nombre.isEmpty()){
            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_LONG).show();
        }else{
            Intent i = new Intent(this, ActivityDatos.class);
            i.putExtra("NOMBRE", nombre);
            startActivity(i);
        }
    }
}
