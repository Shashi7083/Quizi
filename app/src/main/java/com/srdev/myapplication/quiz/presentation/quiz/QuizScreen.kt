package com.srdev.myapplication.quiz.presentation.quiz

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.domain.model.Quiz
import com.srdev.myapplication.quiz.presentation.common.AppBar
import com.srdev.myapplication.quiz.presentation.common.BackHandlerWithConfirmation
import com.srdev.myapplication.quiz.presentation.common.QuizAppBar
import com.srdev.myapplication.quiz.presentation.home.HomeScreen
import com.srdev.myapplication.quiz.presentation.home.HomeScreenState
import com.srdev.myapplication.quiz.presentation.navGraph.Routes
import com.srdev.myapplication.quiz.presentation.quiz.component.QuizButtons
import com.srdev.myapplication.quiz.presentation.quiz.component.QuizInterface
import com.srdev.myapplication.quiz.presentation.quiz.component.ShimmerEffectQuizInterface
import com.srdev.myapplication.quiz.presentation.score.goToHome
import com.srdev.myapplication.quiz.presentation.utils.Constants
import com.srdev.myapplication.quiz.presentation.utils.Dimens
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun QuizScreen(
    numberOfQuiz : Int,
    quizCategory : String,
    quizDifficulty : String,
    quiztype : String,
    event : (QuizScreenEvent) -> Unit,
    state : QuizScreenState,
    navController : NavHostController
){

    /** We will call event under launched effect
     * because we don't know when compose go for recomposition and it will end or not
     */
    LaunchedEffect(key1 = Unit) {

        val difficulty : String = when(quizDifficulty){
            "Medium" -> "medium"
            "Hard" -> "hard"
            else -> "easy"
        }

        val type  = when(quiztype){
            "Multiple Choice" -> "multiple"
            else -> "boolean"
        }

        event(QuizScreenEvent.GetQuizzes(
            numOfQuizzes = numberOfQuiz,
            category = Constants.categoriesMap[quizCategory] ?: -1  ,
            difficulty = difficulty,
            type = type
        ))
    }



    //system ui controller for changing status bar color
    val systemUiController = rememberSystemUiController()

    // Set status bar color to transparent
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true // Adjust based on light/dark mode
    )
    // Ensure the content extends into the system bars
    val activity = LocalContext.current as Activity
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
    }


    var showBackHandler by remember { mutableStateOf(false) }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.top_bg), // Start color
            colorResource(id = R.color.bottom_bg) // End color
        )
    )

    BackHandler {
        showBackHandler = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {
//        QuizAppBar(quizCategory = quizCategory) {
//            
//        }
        AppBar(
            quizCategory = quizCategory,
            onTaskClick = {

            },
            onBackClick = {
//                goToHome(navController= navController)
                showBackHandler = true
            }
        )

        Column(
            modifier = Modifier
                .padding(Dimens.VerySmallPadding)
                .fillMaxSize()
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 25.dp)
                    .background(
                        color = colorResource(id = R.color.white_bg),
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    ),
                    verticalArrangement = Arrangement.SpaceBetween
                ){

                    if(quizFetched(state)){


                        /**
                         * here we implement the horizontal view pager for question
                         */
                        val pagerState = rememberPagerState() {
                            state.quizState.size
                        }

                        HorizontalPager(state = pagerState) {index ->
                            //calling of quiz question and options
                            QuizInterface(
                                qNumber = index + 1 ,
                                quizState = state.quizState[index],
                                onOptionSelected = {selectedIndex ->
                                    event(QuizScreenEvent.SetOptionSelected(index , selectedIndex))
                                },
                            )
                        }



                        //this is for the next  and previous buttons

                        val nxtPrevButtonState   by remember {
                            derivedStateOf {
                                when(pagerState.currentPage){
                                    0 ->{
                                        NextPrevButtonState(
                                           colors =  listOf( R.color.option_stroke, R.color.vivid_orange),
                                            text = listOf("--", "Next")
                                        )

                                    }
                                    state.quizState.size -1 ->{
                                        NextPrevButtonState(
                                            colors = listOf( R.color.vivid_orange, R.color.green),
                                            text = listOf("Previous" , "Submit")
                                        )

                                    }
                                    else ->{
                                        NextPrevButtonState(
                                            colors = listOf( R.color.vivid_orange, R.color.vivid_orange),
                                            text = listOf("Previous" , "Next")
                                        )

                                    }

                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp)
                        ) {
                            val coroutineScope = rememberCoroutineScope()

                            QuizButtons(
                                numberOfQuiz = numberOfQuiz,
                                quizCategory = quizCategory,
                                quizDifficulty = quizDifficulty,
                                quiztype = quiztype,
                                qNumber = pagerState.currentPage +1,
                                onNextClick = {
                                    /**
                                     * if our current page equal to last page then we will navigate to score page
                                     * otherwise we have option for next question
                                     */

                                    if(pagerState.currentPage == state.quizState.size -1){
                                        //// Navigate to Score screen

                                        navController.navigate(Routes.ScoreScreen.passNumOfQuestionsAndCorrectAns(state.quizState.size,state.score)) {
                                            popUpTo(Routes.ScoreScreen.route) {
                                                inclusive = true
                                            }
                                        }

                                    }
                                    else{
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                        }
                                    }

                                },
                                onPreviousClick = {
                                    //suspend function only called from coroutine scope so we created it
                                 coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage -1)
                                    }
                                },
                                modifier = Modifier.padding(bottom = 10.dp),
                                nxtPrevButtonState = nxtPrevButtonState
                            )
                        }
                    }
                }
        }
    }

    if(showBackHandler){
        BackHandlerWithConfirmation (
            title = "Exit Quiz",
            text = "Are you sure, you want to exit this quiz ?",
            confirmText = "Go To Home",
            dismissText = "Cancel",
            onExit = {
                navController.navigate(Routes.HomeScreen.route){
                    popUpTo(Routes.HomeScreen.route){
                        inclusive = true
                    }
                }

            },
            onStay = {
                showBackHandler = false
            }
        )
    }

}


@Composable
fun quizFetched(state: QuizScreenState): Boolean {
    return when{
        state.isLoading ->{
            ShimmerEffectQuizInterface()
            false
        }

        state.quizState?.isNotEmpty() == true -> {
            true
        }

        else ->{
            Text(
                text = state.error.toString(),
                color = colorResource(id = R.color.white)
            )
            false
        }
    }
}


//@RequiresApi(Build.VERSION_CODES.Q)
//@Composable
//@Preview
//fun pv(){
//    QuizScreen(
//       numberOfQuiz = 12, quizCategory = "General Knowledge",
//        quizDifficulty = "Easy",
//        quiztype = "easy",
//        event = {},
//        state = QuizScreenState()
//    )
//}