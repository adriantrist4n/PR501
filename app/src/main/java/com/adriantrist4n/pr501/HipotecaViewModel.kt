package com.adriantrist4n.pr501

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

/**
 * ViewModel que gestiona la lógica de cálculo de hipotecas.
 * Extiende de [ViewModel] para estar acorde a la arquitectura MVVM.
 */
class HipotecaViewModel : ViewModel() {
    // Instancia del simulador de hipotecas
    private val simulador = SimuladorHipoteca()

    /**
     * Inicia el cálculo de la hipoteca basado en el capital y el plazo.
     * Utiliza coroutines para realizar operaciones en un hilo secundario.
     *
     * @param capital El capital de la hipoteca.
     * @param plazo El plazo de la hipoteca en años.
     * @return LiveData que emite el resultado del cálculo de la hipoteca.
     * Emite -1.0 en caso de excepción.
     */
    fun calcularHipoteca(capital: Double, plazo: Int) = liveData() {
        try {
            // Creación de una solicitud de hipoteca
            val solicitud = SimuladorHipoteca.Solicitud(capital, plazo)
            // Calcula la hipoteca y emite el resultado
            val resultado = simulador.calcular(solicitud)
            emit(resultado)
        } catch (e: Exception) {
            // En caso de cualquier excepción, emite -1.0
            emit(-1.0)
        }
    }
}