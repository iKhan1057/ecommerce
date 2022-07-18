package com.ecommerceapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxField(details:String= "",
                  checkedState:MutableState<Boolean>) {
    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checkedState.value,
            modifier = Modifier.padding(2.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Red,
                disabledColor = Color.Red
            ),
            onCheckedChange = { checkedState.value = it }
        )
        Text(
            text = details,
            fontStyle = FontStyle.Normal,
            color = when {
                checkedState.value -> Color.Red
                else -> Color.Red
            },
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 10.dp, 10.dp, 10.dp)
        )
    }
}