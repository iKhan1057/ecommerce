package com.ecommerceapp.screens.timeslots

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.component.AppContent
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun TimeSlotsScreen(navController: NavHostController) {
    AppContent(navController, title = stringResource(id = R.string.timeslot)) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = ArrayList<String>()
                val cal = Calendar.getInstance()
                for (a in 0..10) {
                    cal.add(Calendar.HOUR, 2)
                    list.add(cal.time.toString())
                }
                Log.d("TAG", "TimeSlotsScreen: $list")
            }
        }
    }
}