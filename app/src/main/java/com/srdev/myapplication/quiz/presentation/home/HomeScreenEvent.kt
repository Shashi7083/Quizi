package com.srdev.myapplication.quiz.presentation.home

/**
 * A sealed class representing various events that can occur on the Home Screen of the quiz application.
 * Each event corresponds to a user action for updating quiz settings.
 */
sealed class HomeScreenEvent {

    /**
     * Event to set the number of questions in the quiz.
     * @param numberOfQuizzes The number of questions selected by the user.
     */
    data class SetNumberOfQuizzes(val numberOfQuizzes: Int) : HomeScreenEvent()

    /**
     * Event to set the category of the quiz.
     * @param category The category selected by the user.
     */
    data class SetQuizCategory(val category: String) : HomeScreenEvent()

    /**
     * Event to set the difficulty level of the quiz.
     * @param difficulty The difficulty level selected by the user (e.g., Easy, Medium, Hard).
     */
    data class SetQuizDifficulty(val difficulty: String) : HomeScreenEvent()

    /**
     * Event to set the type of the quiz.
     * @param type The type of quiz selected by the user (e.g., Multiple Choice, True/False).
     */
    data class SetQuizType(val type: String) : HomeScreenEvent()
}
