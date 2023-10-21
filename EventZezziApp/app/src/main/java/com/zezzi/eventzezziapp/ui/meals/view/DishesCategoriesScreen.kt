package com.zezzi.eventzezziapp.ui.meals.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zezzi.eventzezziapp.data.networking.response.DishResponse
import com.zezzi.eventzezziapp.data.networking.response.MealResponse
import com.zezzi.eventzezziapp.navigation.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishesCategoriesScreen(mealName: String,navController: NavController) {
    val viewModel: DishesCategoriesViewModel = viewModel() // Debes crear un ViewModel adecuado para gestionar los platos de esta categoría

    // Recupera la lista de platos (dishes) para la categoría especificada
    val rememberedDishes: MutableState<List<DishResponse>> =
        remember { mutableStateOf(emptyList<DishResponse>()) }

    LaunchedEffect(mealName) {
        try {
            val response = viewModel.getDishes(mealName)
            Log.d("DishesCategoriesScreen", "Response: $response")
            rememberedDishes.value = response?.filter.orEmpty()
        } catch (e: Exception) {
            // Manejar errores si es necesario.
        }
    }

    Scaffold(
        topBar = {
            AppBar(title = mealName, navController = navController) // Muestra el nombre de la categoría en la barra superior
        }
    )  {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = Color(20, 33, 61))
                .verticalScroll(rememberScrollState())
        ) {
            rememberedDishes.value.forEach { dish ->
                // Aquí puedes mostrar los platos (dishes) en Compose, por ejemplo, con un Text
                Text(
                    text = dish.name1,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


