package com.srdev.myapplication.quiz.presentation.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.srdev.myapplication.quiz.presentation.home.HomeScreen
import com.srdev.myapplication.quiz.presentation.home.HomeViewModel
import com.srdev.myapplication.quiz.presentation.quiz.QuizScreen
import com.srdev.myapplication.quiz.presentation.quiz.QuizViewModel
import com.srdev.myapplication.quiz.presentation.score.ScoreScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SetNavGraph(){

    val navController : NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HomeScreen.route){

        composable(route = Routes.HomeScreen.route){

            val viewModel : HomeViewModel = hiltViewModel()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                state = state,
                event = viewModel::onEvent,
                navController = navController
            )
        }


        composable(
            route = Routes.QuizScreen.route,
            arguments = listOf(
                navArgument(ARG_KEY_Quiz_NUMBER){type = NavType.IntType},
                navArgument(ARG_KEY_Quiz_CATEGORY){type = NavType.StringType},
                navArgument(ARG_KEY_Quiz_DIFFICULTY){type = NavType.StringType},
                navArgument(ARG_KEY_Quiz_type){type = NavType.StringType},
            )
        ){

            val numOfQuizzes = it.arguments?.getInt(ARG_KEY_Quiz_NUMBER)
            val category = it.arguments?.getString(ARG_KEY_Quiz_CATEGORY)
            val difficulty = it.arguments?.getString(ARG_KEY_Quiz_DIFFICULTY)
            val type = it.arguments?.getString(ARG_KEY_Quiz_type)

            val quizViewModel : QuizViewModel = hiltViewModel()
            val state by quizViewModel.quizList.collectAsState()

            QuizScreen(
                numberOfQuiz = numOfQuizzes!!,
                quizCategory = category!!,
                quizDifficulty = difficulty!!,
                quiztype = type!!,
                event = quizViewModel::onEvent,
                state = state,
                navController = navController
            )
        }

        composable(
            route = Routes.ScoreScreen.route,
            arguments = listOf(
                navArgument(NOQ_KEY){type = NavType.IntType},
                navArgument(CORRECT_ANS_KEY){type = NavType.IntType}
            ),
        ){
            val numOfQuestions = it.arguments?.getInt(NOQ_KEY)
            val numOfCorrectAns = it.arguments?.getInt(CORRECT_ANS_KEY)

            ScoreScreen(
                numOfQuestions = numOfQuestions!!,
                numOfCorrectAns = numOfCorrectAns!!,
                navController = navController)
        }
    }
}