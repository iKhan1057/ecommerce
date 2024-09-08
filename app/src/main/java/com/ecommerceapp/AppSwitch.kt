package com.ecommerceapp

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppSwitch(modifier: Modifier = Modifier){
    var checked by remember { mutableStateOf(true) }
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.onBackground,
            checkedTrackColor = MaterialTheme.colors.primary,
            uncheckedThumbColor = Color.DarkGray,
            uncheckedTrackColor = Color.Gray,
        )
    )
}

@Composable
fun AppText(modifier: Modifier = Modifier,text:String, isUpperCase:Boolean){
    Text(
        text = if(isUpperCase)text.uppercase() else text,
        modifier = modifier,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}