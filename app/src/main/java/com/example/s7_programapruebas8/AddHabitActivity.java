package com.example.s7_programapruebas8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etDescripcion = findViewById(R.id.etDescripcion);
        EditText etTelefono = findViewById(R.id.etTelefono);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ponle un nombre al hábito", Toast.LENGTH_SHORT).show();
                return;
            }

            Habit nuevoHabito = new Habit(nombre, descripcion, 0, telefono);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("nuevoHabito", nuevoHabito);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}