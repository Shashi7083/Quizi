package com.srdev.myapplication.quiz.presentation.home

/**
 * A data class representing the state of the Home Screen in the quiz application.
 * This holds the current configurations for the quiz that the user can customize.
 *
 * @property numberOfQuiz The number of questions in the quiz. Default is 10.
 * @property category The selected category of the quiz. Default is "General Knowledge".
 * @property difficulty The difficulty level of the quiz. Default is "Easy".
 * @property type The type of quiz questions. Default is "Multiple Choice".
 */
data class HomeScreenState(
    val numberOfQuiz: Int = 10,
    val category: String = "General Knowledge",
    val difficulty: String = "Easy",
    val type: String = "Multiple Choice"
)
