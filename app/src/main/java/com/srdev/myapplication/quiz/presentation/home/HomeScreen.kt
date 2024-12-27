package com.srdev.myapplication.quiz.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.srdev.myapplication.quiz.presentation.common.AppDropDownMenu
import com.srdev.myapplication.quiz.presentation.common.ButtonBox
import com.srdev.myapplication.quiz.presentation.home.component.HomeHeader
import com.srdev.myapplication.quiz.presentation.navGraph.Routes
import com.srdev.myapplication.quiz.presentation.utils.Constants
import com.srdev.myapplication.quiz.presentation.utils.Dimens

/**
 * HomeScreen is the main screen of the quiz application where users can configure quiz settings.
 * It includes dropdown menus to select the number of questions, category, difficulty, and type,
 * and a button to navigate to the quiz screen.
 *
 * @param state Current state of the HomeScreen, includes quiz configurations.
 * @param event A lambda function to handle events triggered from UI interactions.
 * @param navController Used for navigation to other screens.
 *
 * TODO Later We have to modify the ui
 */

@Composable
fun HomeScreen(
    state : HomeScreenState,
    event : (HomeScreenEvent)->Unit,
    navController: NavController
){
    /** Column to structure the layout vertically **/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) /** Enables vertical scrolling for smaller screen **/

    ) {
        /** Header for the home screen */
        HomeHeader()

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        /* Dropdown menu for selecting number of questions*/
        AppDropDownMenu(menuName = "Number of Questions", menuList = Constants.numbersAsString, text = state.numberOfQuiz.toString(), onDropDownClick = {
            event(HomeScreenEvent.SetNumberOfQuizzes(it.toInt())) /* updates the number of question **/
        })


        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        /* Dropdown menu for selecting quiz category */
        AppDropDownMenu(menuName = "Select Category:", menuList = Constants.categories, text = state.category, onDropDownClick = {
            event(HomeScreenEvent.SetQuizCategory(it))   /*updates the Quiz Category */
        })

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        /* Dropdown menu for selecting the difficulty of quiz*/
        AppDropDownMenu(menuName = "Select Difficulty:", menuList = Constants.difficulty, text = state.difficulty, onDropDownClick = {
            event(HomeScreenEvent.SetQuizDifficulty(it))  /* updates the quiz difficulty */
        })

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        /*  Dropdown menu for selecting quit type */
        AppDropDownMenu(menuName = "Select Type:", menuList = Constants.type, text = state.type, onDropDownClick = {
            event(HomeScreenEvent.SetQuizType(it))  /* updates the quiz type */
        })

        Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))

        /**
         * Button to generate quiz and navigate to Quiz Screen
         * @param text , that show's on button
         * @param padding to give padding , so we can give custome padding while using the button multiple times
         */
        ButtonBox(
            text = "Generate Quiz",
            padding = Dimens.MediumPadding,
        ){
            navController.navigate(
                route = Routes.QuizScreen.passQuizParams(state.numberOfQuiz,state.category,state.difficulty,state.type)
            )
        }


    }
}

@Composable
@Preview
fun Preview(){
    HomeScreen(
        state = HomeScreenState(),
        event = {

        },
        navController = rememberNavController()
    )
}