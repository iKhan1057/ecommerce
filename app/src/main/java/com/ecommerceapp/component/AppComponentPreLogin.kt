package com.ecommerceapp.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ecommerceapp.R

@Composable
fun AppComponentPreLogin(
    navController: NavHostController,
    title: String = "",
    showBack: Boolean = true,
    BodyContent: @Composable () -> Unit
) {
    Column() {
        TopBar(name = title, showBack = showBack) {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(
            modifier = Modifier.padding(all = 20.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState(0)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_rounded),
                    contentDescription = "app logo",
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                BodyContent()
            }
        }
    }
}

@Composable
fun TopBar(
    name: String,
    showBack: Boolean = true,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 10.dp, end = 10.dp)
            .background(color = Color(0xFFFFFFFF)),
    ) {
        if (showBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .clickable { onClick() }
                    .align(alignment = Alignment.CenterVertically), tint = Color.Red
            )
            Spacer(modifier = Modifier.width(20.dp))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(10.dp),
                color = Color(0xFFD63B0A),
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
