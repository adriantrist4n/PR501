package com.adriantrist4n.pr501.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adriantrist4n.pr501.HipotecaViewModel
import com.adriantrist4n.pr501.screens.components.CustomRow
import com.adriantrist4n.pr501.ui.theme.BackgroundScreen

/**
 * Función Composable que representa la pantalla para calcular hipotecas.
 * Esta función actúa como la vista en la arquitectura MVVM.
 *
 * Utiliza el [HipotecaViewModel] para realizar operaciones de lógica de negocio.
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CalcularScreen() {

    // Instancia del ViewModel para manejar los cálculos de hipotecas
    val viewModel: HipotecaViewModel = viewModel()

    // Variables de estado para mantener las entradas del usuario y los resultados
    var capital by remember { mutableStateOf("") }
    var plazo by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .background(color = BackgroundScreen)
            .padding(top = 70.dp, bottom = 60.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            CustomRow("Calcular Presupuesto de la Hipoteca") {
                // Campo de texto para el capital
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        value = capital,
                        onValueChange = { capital = it },
                        label = { Text("Capital") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Campo de texto para el plazo
                    TextField(
                        value = plazo,
                        onValueChange = { plazo = it },
                        label = { Text("Plazo (años)") }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Botón para calcular la hipoteca
                    Button(onClick = {
                        val capitalNumerico = capital.toDoubleOrNull() ?: 0.0
                        val plazoNumerico = plazo.toIntOrNull() ?: 0

                        viewModel.calcularHipoteca(capitalNumerico, plazoNumerico)
                            .observeForever { resultadoCalculado ->
                                resultado = resultadoCalculado.toString()
                                if (resultado == "-1.0"){
                                    resultado = "ERROR: El capital minimo a solicitar es 10.000"
                                }
                            }
                    }) {
                        Text("Calcular")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    // Mostrar el resultado de la hipoteca
                    Text(text = "Resultado: ${resultado ?: ""}")
                }
            }
        }
    }
}