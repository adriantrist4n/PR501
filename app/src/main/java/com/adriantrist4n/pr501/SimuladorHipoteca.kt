package com.adriantrist4n.pr501

/**
 * Clase que representa el simulador de hipotecas.
 * Actúa como el modelo en la arquitectura MVVM y se encarga de realizar
 * los cálculos relacionados con la hipoteca.
 */
class SimuladorHipoteca {

    /**
     * Clase de datos que representa una solicitud de hipoteca.
     *
     * @property capital La cantidad de capital solicitada para la hipoteca.
     * @property plazo El plazo en años para el pago de la hipoteca.
     */
    data class Solicitud(val capital: Double, val plazo: Int)

    /**
     * Calcula la cuota mensual de una hipoteca basada en el capital y el plazo.
     *
     * @param solicitud La solicitud de hipoteca que contiene el capital y el plazo.
     * @return La cuota mensual a pagar.
     * @throws IllegalArgumentException Si el capital es menor a 10,000.
     */
    fun calcular(solicitud: Solicitud): Double {
        if (solicitud.capital < 10000) {  // Restricción de capital mínimo
            return -1.0
        }

        // Tasa de interés anual
        val interes = 0.01605
        // Conversión de tasa anual a tasa mensual
        val tasaMensual = interes / 12
        // Total de meses para el plazo
        val plazoMeses = solicitud.plazo * 12
        // Cálculo del factor para la fórmula de la cuota mensual
        val factor = 1 - Math.pow(1 + tasaMensual, (-plazoMeses).toDouble())
        // Cálculo de la cuota mensual
        val cuotaMensual = solicitud.capital * tasaMensual / factor

        return cuotaMensual
    }
}
