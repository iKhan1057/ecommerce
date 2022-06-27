package com.ecommerceapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecommerceapp.R
import com.ecommerceapp.navigation.AppScreenName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            color = Color(0xFFD63B0A),
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
    Button(
        onClick = { onButtonClick() },
        colors = ButtonDefaults.buttonColors(Color(0xFF1D065C))
    ) {
        Text(
            text = buttonname,
            modifier = modifier.padding(10.dp),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun AppContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isHome: Boolean = false,
    title: String = "",
    leftIcon: ImageVector = Icons.Default.ArrowBack,
    LeftIconClick: () -> Unit = {},
    drawerContent: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    if (isHome) {
        Scaffold(scaffoldState = scaffoldState,
            topBar = { AppTopBar(navController, isHome, title, leftIcon, scope, scaffoldState) },
            drawerContent = {
                AppDrawerView(
                    navController,
                    scope,
                    scaffoldState
                )
            }) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                drawerContent()
            }
        }
    } else {
        Scaffold(scaffoldState = scaffoldState,
            topBar = { AppTopBar(navController, isHome, title, leftIcon, scope, scaffoldState) })
        { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                drawerContent()
            }
        }
    }
}

@Composable
fun AppDrawerView(
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.home)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.address)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.ADDRESS.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.myorders)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.MYORDER.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.profile)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.PROFILE.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.feedback)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.FEEDBACK.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.settings)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.SETTINGS.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.aboutus)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.ABOUTUS.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.contactus)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.CONTACTUS.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.terms)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.TERMS.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.privacy)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.PRIVACY.name)
                }
            }
            Divider(modifier = Modifier.height(2.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                buttonname = stringResource(id = R.string.logout)
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                    navController.navigate(AppScreenName.SIGNIN.name)
                }
            }
        }
    }
}

@Composable
fun AppTopBar(
    navController: NavHostController,
    isHome: Boolean,
    title: String,
    leftIcon: ImageVector,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        backgroundColor = Color(0xFF1D065C),
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        if (isHome)
                            scaffoldState.drawerState.open()
                        else
                            navController.popBackStack()
                    }
                },
            ) {
                if (isHome)
                    Icon(
                        Icons.Rounded.Menu,
                        contentDescription = ""
                    )
                else
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = ""
                    )
            }
        })
}