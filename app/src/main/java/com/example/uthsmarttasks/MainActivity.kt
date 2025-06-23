package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.view.*
import com.example.uthsmarttasks.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationGraph()
        }
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        //NavHost(navController = navController, startDestination = "googleSignIn")
        composable("googleSignIn") {
            GoogleSignInScreen(navController)
        }
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("firstIntroduction") {
            FirstIntroduction(navController)
        }
        composable("secondIntroduction") {
            SecondIntroduction(navController)
        }
        composable("thirdIntroduction") {
            ThirdIntroduction(navController)
        }
        composable(BottomNavItem.Home.route) { HomeScreen(navController = navController) }
        composable(BottomNavItem.Document.route) { MenuScreen() }
        composable(BottomNavItem.Setting.route) { SettingScreen() }
        composable(BottomNavItem.Date.route) { DateScreen() }
        composable("add_task") {
            val viewModel: TaskViewModel = viewModel()
            AddTaskScreen(navController = navController, viewModel = viewModel)
        }

        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            TaskDetailScreen(navController = navController, taskId = taskId)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    NavigationGraph()
}
