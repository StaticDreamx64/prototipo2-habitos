package com.example.s7_programapruebas8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        Habit habito = (Habit) getIntent().getSerializableExtra("habito");

        EditText etNombre = findViewById(R.id.etNombreEditar);
        EditText etDescripcion = findViewById(R.id.etDescripcionEditar);
        EditText etRacha = findViewById(R.id.etRachaEditar);
        Button btnGuardar = findViewById(R.id.btnGuardarEdicion);

        // Precargamos los datos actuales
        etNombre.setText(habito.getNombre());
        etDescripcion.setText(habito.getDescripcion());
        etRacha.setText(String.valueOf(habito.getDiasRacha()));

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String rachaTexto = etRacha.getText().toString().trim();

            if (nombre.isEmpty()) {
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                return;
            }

            int racha = rachaTexto.isEmpty() ? 0 : Integer.parseInt(rachaTexto);

            Habit habitoEditado = new Habit(nombre, descripcion, racha, habito.getTelefono());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("habitoEditado", habitoEditado);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}