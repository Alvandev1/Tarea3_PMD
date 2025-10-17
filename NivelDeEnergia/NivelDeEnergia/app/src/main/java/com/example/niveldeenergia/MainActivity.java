package com.example.niveldeenergia;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etHorasSueno, etCafes, etActividadMin;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHorasSueno = findViewById(R.id.etHorasSueno);
        etCafes = findViewById(R.id.etCafes);
        etActividadMin = findViewById(R.id.etActividadMin);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);


        btnCalcular.setOnClickListener(v -> calcularEnergia());
    }

    private void calcularEnergia() {
        String horasStr = etHorasSueno.getText().toString().trim();
        String cafesStr = etCafes.getText().toString().trim();
        String actividadStr = etActividadMin.getText().toString().trim();

        if (horasStr.isEmpty() || cafesStr.isEmpty() || actividadStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_campos), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double horas = Double.parseDouble(horasStr);
            int cafes = Integer.parseInt(cafesStr);
            int actividadMin = Integer.parseInt(actividadStr);

            if (horas < 0 || cafes < 0 || actividadMin < 0) {
                Toast.makeText(this, getString(R.string.error_valores), Toast.LENGTH_SHORT).show();
                return;
            }

            double sueno = Math.min(horas, 10) * 6.0;
            double cafe;
            if (cafes <= 3)      cafe = cafes * 5.0;
            else if (cafes <= 5) cafe = 15.0;
            else                 cafe = 15.0 - 5.0 * (cafes - 5);
            double act = Math.min(actividadMin, 120) * (30.0 / 120.0);

            double total = sueno + cafe + act;
            if (total > 100) total = 100;
            if (total < 0) total = 0;

            String etiqueta;
            if (total < 40)      etiqueta = getString(R.string.nivel_bajo);
            else if (total < 70) etiqueta = getString(R.string.nivel_medio);
            else                 etiqueta = getString(R.string.nivel_alto);

            String resultado = getString(R.string.resultado_energia, (int)Math.round(total), etiqueta);
            tvResultado.setText(resultado);

        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_numeros), Toast.LENGTH_SHORT).show();
        }
    }
}
