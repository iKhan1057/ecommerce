package com.ecommerceapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecommerceapp.screens.TimeSlotsScreen
import com.ecommerceapp.screens.address.AddressScreen
import com.ecommerceapp.screens.cart.CartScreen
import com.ecommerceapp.screens.changepwd.ChangePasswordScreen
import com.ecommerceapp.screens.forgetpwd.ForgetPasswordScreen
import com.ecommerceapp.screens.home.HomeScreen
import com.ecommerceapp.screens.offers.OffersScreen
import com.ecommerceapp.screens.payment.PaymentScreen
import com.ecommerceapp.screens.profile.ProfileScreen
import com.ecommerceapp.screens.signin.SignInScreen
import com.ecommerceapp.screens.signup.SignUpScreen
import com.ecommerceapp.screens.splash.SplashScreen
import com.ecommerceapp.screens.success.SuccessScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreenName.SPLASH.name) {

        composable(route = AppScreenName.SPLASH.name) { navBackStackEntry ->
            SplashScreen(navController)
        }

        composable(route = AppScreenName.SIGNIN.name) { navBackStackEntry ->
            SignInScreen(navController)
        }

        composable(route = AppScreenName.SIGNUP.name) { navBackStackEntry ->
            SignUpScreen(navController)
        }

        composable(route = AppScreenName.FORGETPASSWORD.name) { navBackStackEntry ->
            ForgetPasswordScreen(navController)
        }

        composable(route = AppScreenName.HOME.name) { navBackStackEntry ->
            HomeScreen(navController)
        }

        composable(route = AppScreenName.CHANGEPASSWORD.name) { navBackStackEntry ->
            ChangePasswordScreen(navController)
        }

        composable(route = AppScreenName.PROFILE.name) { navBackStackEntry ->
            ProfileScreen(navController)
        }

        composable(route = AppScreenName.CART.name) { navBackStackEntry ->
            CartScreen(navController)
        }

        composable(route = AppScreenName.ADDRESS.name) { navBackStackEntry ->
            AddressScreen(navController)
        }

        composable(route = AppScreenName.TIMESLOT.name) { navBackStackEntry ->
            TimeSlotsScreen(navController)
        }

        composable(route = AppScreenName.OFFER.name) { navBackStackEntry ->
            OffersScreen(navController)
        }

        composable(route = AppScreenName.PAYMENT.name) { navBackStackEntry ->
            PaymentScreen(navController)
        }

        composable(route = AppScreenName.SUCCESS.name) { navBackStackEntry ->
            SuccessScreen(navController)
        }
    }
}