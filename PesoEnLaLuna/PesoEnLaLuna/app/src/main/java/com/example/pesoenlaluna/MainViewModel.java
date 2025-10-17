package com.example.pesoenlaluna;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> pesoTierra = new MutableLiveData<>("");
    public MutableLiveData<String> resultado = new MutableLiveData<>("");

    public void calcularPeso(String planeta) {
        try {
            double peso = Double.parseDouble(pesoTierra.getValue());
            double gravedad;

            switch (planeta) {
                case "Luna":
                    gravedad = 0.165;
                    break;
                case "Marte":
                    gravedad = 0.38;
                    break;
                case "Júpiter":
                    gravedad = 2.34;
                    break;
                case "Venus":
                    gravedad = 0.91;
                    break;
                default:
                    gravedad = 1.0;
                    break;
            }

            double pesoFinal = peso * gravedad;
            resultado.setValue(String.format("Tu peso en %s sería %.2f kg", planeta, pesoFinal));

        } catch (Exception e) {
            resultado.setValue("Por favor introduce un número válido");
        }
    }
}

