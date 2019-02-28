package com.example.manpa.appalimentossqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manpa.appalimentossqlite.db.AlimentosDatabase;
import com.example.manpa.appalimentossqlite.model.Alimento;

public class ActivityDatos extends AppCompatActivity {
    String nombreI;

    TextView tvNombre;
    TextView tvTipo;
    TextView tvOrigen;
    TextView tvNutrientes;
    TextView tvFuncion;
    AlimentosDatabase ads;
    Alimento alimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        tvNombre=findViewById(R.id.tvNombreD);
        tvTipo=findViewById(R.id.tvTipo);
        tvOrigen=findViewById(R.id.tvOrigen);
        tvNutrientes=findViewById(R.id.tvNutrientes);
        tvFuncion=findViewById(R.id.tvFunciones);

        nombreI=getIntent().getStringExtra("NOMBRE");

        ads= new AlimentosDatabase(this);
        alimento=ads.consultarAlimento(nombreI);

        tvNombre.setText(alimento.getNombre());
        tvTipo.setText(alimento.getTipo());
        tvOrigen.setText(alimento.getOrigen());
        tvNutrientes.setText(alimento.getNutrientes());
        tvFuncion.setText(alimento.getFuncion());

    }
    public void borrar(View view){

        ads.borrarAlimento(alimento.getId());

        finish();
        Toast.makeText(this, "Se ha borrado correctamente", Toast.LENGTH_LONG).show();
    }

    public void volver(View view){
        finish();
    }
}
