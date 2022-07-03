package com.ecommerceapp.screens.cms


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppContent

@Composable
fun AboutUsScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.aboutus)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val scroll = rememberScrollState(0)
                val description = stringResource(id = R.string.aboutus_content)
                Text(
                    text = description,
                    modifier = Modifier
                        .padding(15.dp)
                        .verticalScroll(scroll),
                    color = Color(0xFFD63B0A),
                    fontSize = 18.sp
                )
            }
        }
    }
}