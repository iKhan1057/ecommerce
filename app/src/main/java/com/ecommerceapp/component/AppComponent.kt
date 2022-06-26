package com.ecommerceapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecommerceapp.ui.theme.Txt

@Composable
fun ScreenGreeting(
    name: String,
    onClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            modifier = Modifier.clickable { onClick() },
        )

        Divider(modifier = Modifier.height(20.dp))

        Text(
            text = name,
            modifier = Modifier.padding(10.dp),
            color = Color(0xFF1D065C),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    buttonname: String = "",
    onButtonClick: () -> Unit = {}
) {
    Button(onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(Color(0xFF1D065C))) {
        Text(
            text = "Hello $buttonname!",
            modifier = Modifier.padding(10.dp),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}