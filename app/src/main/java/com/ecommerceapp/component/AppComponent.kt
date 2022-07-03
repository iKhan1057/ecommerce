package com.ecommerceapp.component

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ecommerceapp.model.profile.Profile
import com.ecommerceapp.navigation.AppScreenName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun loadWebUrl(url: String) {
    val context = LocalContext.current
    AndroidView(factory = {
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}

@Composable
fun ScreenGreeting(
    name: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(0xFFE91E63)),
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .clickable { onClick() }
                .align(alignment = CenterVertically), tint = Color.Red
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = name,
            modifier = Modifier
                .padding(10.dp)
                .align(alignment = CenterVertically),
            color = Color(0xFFD63B0A),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ShowToast(message: String = "") {
    val context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    buttonname: String = "",
    onButtonClick: () -> Unit = {}
) {
    Button(onClick = { onButtonClick() }
    ) {
        Text(
            text = buttonname.uppercase(),
            modifier = modifier,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AppDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(10.dp),
        color = Color.Transparent,
    )
}

@Composable
fun AppContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isHome: Boolean = false,
    title: String = "",
    profile: Profile = Profile(),
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
                    profile,
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
    profile: Profile,
    scaffoldState: ScaffoldState
) {
    val scrollState = rememberScrollState(0)

    val drawerItems = arrayListOf(
        AppScreenName.HOME.name,
        AppScreenName.ADDRESS.name,
        AppScreenName.MY_FAVORITE_CARTS.name,
        AppScreenName.MY_WALLET.name,
        AppScreenName.OFFERS.name,
        AppScreenName.MY_ORDERS.name,
        AppScreenName.RATING.name,
        AppScreenName.FEEDBACK.name,
        AppScreenName.SETTINGS.name,
        AppScreenName.ABOUT_US.name,
        AppScreenName.CONTACT_US,
        AppScreenName.TERMS_AND_CONDITIONS.name,
        AppScreenName.PRIVACY_POLICY.name,
        AppScreenName.LOGOUT.name
    )
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(color = Color(color = profile.bannerColor), shape = RectangleShape)
                    .clickable {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            navController.navigate(AppScreenName.PROFILE.name)
                        }
                    },
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    ProfileImage(profile.image)
                    Column(
                        modifier = Modifier.padding(all = 5.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        if (profile.rating > 0)
                            Spacer(modifier = Modifier.height(20.dp))
                        else
                            Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = profile.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        if (profile.rating > 0) {
                            Row {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.White,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        ) {
                                            append("Rating: ")
                                        }
                                        withStyle(style = SpanStyle(color = Color.White)) {
                                            append(profile.rating.toString())
                                        }
                                    },
                                    fontSize = 18.sp,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "star",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(15.dp)
                                        .align(alignment = CenterVertically)
                                )
                            }
                        }
                    }
                }
            }

            AppDivider(modifier = Modifier.height(2.dp))

            LazyColumn() {
                items(items = drawerItems) { items ->
                    AppButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        buttonname = items.toString().replace("_", " ").replace("AND", "&")
                    ) {
                        when (items) {
                            AppScreenName.HOME.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }

                            AppScreenName.ADDRESS.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.ADDRESS.name)
                                }
                            }

                            AppScreenName.OFFERS.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.OFFERS.name)
                                }
                            }

                            AppScreenName.MY_WALLET.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.MY_WALLET.name)
                                }
                            }

                            AppScreenName.MY_FAVORITE_CARTS.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.MY_FAVORITE_CARTS.name)
                                }
                            }

                            AppScreenName.MY_ORDERS.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.MY_ORDERS.name)
                                }
                            }

                            AppScreenName.RATING.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.RATING.name)
                                }
                            }
                            AppScreenName.FEEDBACK.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.FEEDBACK.name)
                                }
                            }
                            AppScreenName.SETTINGS.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.SETTINGS.name)
                                }
                            }
                            AppScreenName.ABOUT_US.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.ABOUT_US.name)
                                }
                            }
                            AppScreenName.CONTACT_US -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.CONTACT_US.name)
                                }
                            }
                            AppScreenName.TERMS_AND_CONDITIONS.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.TERMS_AND_CONDITIONS.name)
                                }
                            }
                            AppScreenName.PRIVACY_POLICY.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.PRIVACY_POLICY.name)
                                }
                            }
                            AppScreenName.LOGOUT.name -> {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    navController.navigate(AppScreenName.SIGNIN.name)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileImage(image: String) {
    Surface(shape = CircleShape, border = BorderStroke(3.dp, Color.LightGray), elevation = 10.dp) {
        AsyncImage(
            model = image,
            contentDescription = "profile picture",
            modifier = Modifier
                .size(100.dp)
                .padding(2.dp)
        )
    }
    Spacer(modifier = Modifier.width(10.dp))
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
        backgroundColor = MaterialTheme.colors.primary,
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
                        contentDescription = "",
                        tint = Color.White
                    )
                else
                    Icon(
                        Icons.Rounded.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
            }
        })
}
