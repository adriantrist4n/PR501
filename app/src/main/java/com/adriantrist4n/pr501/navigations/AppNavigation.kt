package com.adriantrist4n.pr501.navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adriantrist4n.pr501.HipotecaViewModel
import com.adriantrist4n.pr501.screens.CalcularScreen



/**
 * Configura la navegación de la aplicación utilizando el componente NavHost.
 *
 * Define las rutas y los componentes asociados para las diferentes pantallas de la aplicación.
 * Utiliza un objeto NavController para manejar la navegación entre pantallas y un ViewModel
 * para compartir datos entre ellas.
 *
 * @param navController Controlador de navegación para manejar la transición entre pantallas.
 */
@Composable
fun AppNavigation(navController: NavController){
    NavHost(navController = navController as NavHostController, startDestination = AppScreens.Calcular.route ){
        composable(route = AppScreens.Calcular.route){
            CalcularScreen()
        }
    }
}