package com.mikekorel.mobilebankingdemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mikekorel.mobilebankingdemo.core.Constants
import com.mikekorel.mobilebankingdemo.presentation.accountdetails.components.AccountDetailsScreen
import com.mikekorel.mobilebankingdemo.presentation.accountslist.components.AccountsListScreen
import com.mikekorel.mobilebankingdemo.presentation.ui.theme.MobileBankingDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileBankingDemoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AccountsListScreen.route
                ) {
                    composable(Screen.AccountsListScreen.route) {
                        AccountsListScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AccountDetailsScreen.route + "/{${Constants.PARAM_ACCOUNT_ID}}"
                    ) {
                        AccountDetailsScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobileBankingDemoTheme {
        Greeting("Android")
    }
}