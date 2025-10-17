package com.example.pesoenlaluna;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.pesoenlaluna.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vinculamos el XML con Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Creamos el ViewModel
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Conectamos el ViewModel con el layout
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Lista de planetas para el Spinner
        String[] planetas = {"Luna", "Marte", "Júpiter", "Venus"};

        // Adaptador del Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                planetas
        );
        binding.spinnerPlaneta.setAdapter(adapter);

        // Acción al presionar el botón Calcular
        binding.btnCalcular.setOnClickListener(v -> {
            String planeta = binding.spinnerPlaneta.getSelectedItem().toString();
            viewModel.calcularPeso(planeta);
        });
    }
}
