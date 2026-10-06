package com.example.s7_programapruebas8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HabitDetailActivity extends AppCompatActivity {

    private Habit habito;
    private static final int REQUEST_FOTO = 2;
    private static final int REQUEST_EDITAR = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);

        habito = (Habit) getIntent().getSerializableExtra("habito");

        TextView txtNombre = findViewById(R.id.txtNombreHabito);
        TextView txtDescripcion = findViewById(R.id.txtDescripcionHabito);
        TextView txtRacha = findViewById(R.id.txtRachaHabito);
        ImageView imgEvidencia = findViewById(R.id.imgEvidencia);

        txtNombre.setText(habito.getNombre());
        txtDescripcion.setText(habito.getDescripcion());
        txtRacha.setText("Racha actual: " + habito.getDiasRacha() + " días");

        // Intent explícito #3: HabitDetail -> EditHabitActivity
        Button btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(HabitDetailActivity.this, EditHabitActivity.class);
            intent.putExtra("habito", habito);
            startActivityForResult(intent, REQUEST_EDITAR);
        });

        // Intent implícito #1: Compartir (ACTION_SEND)
        Button btnCompartir = findViewById(R.id.btnCompartir);
        btnCompartir.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,
                    "¡Llevo " + habito.getDiasRacha() + " días con mi hábito: " + habito.getNombre() + "! 💪");
            startActivity(Intent.createChooser(intent, "Compartir progreso"));
        });

        // Intent implícito #2: Ver web de tips (ACTION_VIEW)
        Button btnTips = findViewById(R.id.btnTips);
        btnTips.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.healthline.com/health/how-to-build-a-habit"));
            startActivity(intent);
        });

        // Intent implícito #3: Poner recordatorio (ACTION_SET_ALARM)
        Button btnRecordatorio = findViewById(R.id.btnRecordatorio);
        btnRecordatorio.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, habito.getNombre());
                intent.putExtra(AlarmClock.EXTRA_HOUR, 20);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, 0);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "No hay app de reloj disponible en este dispositivo", Toast.LENGTH_SHORT).show();
            }
        });

        // Intent implícito #4: Tomar foto (ACTION_IMAGE_CAPTURE)
        Button btnFoto = findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, REQUEST_FOTO);
            }else {
                Toast.makeText(this, "No hay app de cámara disponible en este dispositivo", Toast.LENGTH_SHORT).show();
            }
        });

        // Intent implícito #5: Llamar a compañero (ACTION_DIAL)
        Button btnLlamar = findViewById(R.id.btnLlamar);
        btnLlamar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + habito.getTelefono()));
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_FOTO && resultCode == RESULT_OK && data != null) {
            ImageView imgEvidencia = findViewById(R.id.imgEvidencia);
            imgEvidencia.setImageBitmap((android.graphics.Bitmap) data.getExtras().get("data"));
        }

        if (requestCode == REQUEST_EDITAR && resultCode == RESULT_OK && data != null) {
            habito = (Habit) data.getSerializableExtra("habitoEditado");
            TextView txtNombre = findViewById(R.id.txtNombreHabito);
            TextView txtDescripcion = findViewById(R.id.txtDescripcionHabito);
            TextView txtRacha = findViewById(R.id.txtRachaHabito);
            txtNombre.setText(habito.getNombre());
            txtDescripcion.setText(habito.getDescripcion());
            txtRacha.setText("Racha actual: " + habito.getDiasRacha() + " días");
        }
    }
}