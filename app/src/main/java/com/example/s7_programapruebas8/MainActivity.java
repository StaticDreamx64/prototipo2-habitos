package com.example.s7_programapruebas8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Habit> listaHabitos = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewHabitos);
        Button btnAgregar = findViewById(R.id.btnAgregarHabito);

        // Datos de ejemplo para que la lista no esté vacía al abrir
        listaHabitos.add(new Habit("Tomar agua", "Beber 2 litros al día", 3, "+56912345678"));
        listaHabitos.add(new Habit("Leer", "Leer 20 minutos antes de dormir", 7, "+56987654321"));

        actualizarLista();

        // Intent explícito #1: Main -> AddHabitActivity
        btnAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
            startActivityForResult(intent, 1);
        });

        // Intent explícito #2: Main -> HabitDetailActivity
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Habit habitoSeleccionado = listaHabitos.get(position);
            Intent intent = new Intent(MainActivity.this, HabitDetailActivity.class);
            intent.putExtra("habito", habitoSeleccionado);
            startActivity(intent);
        });
    }

    private void actualizarLista() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Habit h : listaHabitos) {
            nombres.add(h.getNombre() + " — racha: " + h.getDiasRacha() + " días");
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombres);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Habit nuevoHabito = (Habit) data.getSerializableExtra("nuevoHabito");
            listaHabitos.add(nuevoHabito);
            actualizarLista();
        }
    }
}