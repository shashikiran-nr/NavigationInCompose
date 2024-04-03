package com.navigationincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.navigationincompose.ui.theme.NavigationInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration") {
        composable(route = "registration") {
            /*
             One way is to pass the nav controller as a
             parameter in the compose functions like below
             */

//            RegistrationScreen(navController)

            /*
             Other approach is passing the functions to compose
             where this functions will have all to login of navigation and navController
             */
            RegistrationScreen {
                navController.navigate("login")
            }

        }
        composable(route = "login") {
            LoginScreen {
                navController.navigate("main/${it}")
            }
        }
        // Make sure to use the same navArgument "name".
        // For sending multiple args add it same way {email}/{}/{}...
        composable(route = "main/{email}", arguments = listOf(
            navArgument("email"){
                type = NavType.StringType
            }
        )) {
            val email = it.arguments!!.getString("email")
            MainScreen(email!!)
        }
    }
}


/*
    Declaring all the screens.
 */
@Composable
fun RegistrationScreen(navigationController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registration",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.clickable {
                    navigationController.navigate("login")
                }
            )
        }

    }
}

/*
    This method is used to show other approach of using navigation in compose.
 */
@Composable
fun RegistrationScreen(onclick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registration Screen",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.clickable {
                    onclick()
                }
            )
        }

    }
}

@Composable
fun LoginScreen(onClickPassDataToMainScreen: (email: String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login Screen",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.clickable {
                    onClickPassDataToMainScreen("sosikrain@gmail.com")
                }
            )
        }

    }
}

@Composable
fun MainScreen(email: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Main Screen: $email ",
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
