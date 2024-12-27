package com.srdev.myapplication.quiz.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * ViewModel for the Home Screen of the quiz application.
 * It manages the state of the Home Screen and processes events to update the state.
 */
class HomeViewModel : ViewModel() {

    // Backing property for the Home Screen state, initialized with default values
    private val _homeState = MutableStateFlow(HomeScreenState())

    /**
     * Publicly exposed state flow for observing changes in the Home Screen state.
     */
    val homeState = _homeState

    /**
     * Handles user events and updates the state accordingly.
     *
     * @param event The event triggered by user interaction, such as changing quiz settings.
     */
    fun onEvent(event: HomeScreenEvent) {
        when (event) {

            // Updates the number of quiz questions
            is HomeScreenEvent.SetNumberOfQuizzes -> {
                _homeState.value = homeState.value.copy(numberOfQuiz = event.numberOfQuizzes)
            }

            // Updates the quiz category
            is HomeScreenEvent.SetQuizCategory -> {
                _homeState.value = homeState.value.copy(category = event.category)
            }

            // Updates the quiz difficulty level
            is HomeScreenEvent.SetQuizDifficulty -> {
                _homeState.value = homeState.value.copy(difficulty = event.difficulty)
            }

            // Updates the quiz type
            is HomeScreenEvent.SetQuizType -> {
                _homeState.value = homeState.value.copy(type = event.type)
            }

            // Handles unexpected or unimplemented events (optional placeholder)
            else -> {}
        }
    }
}
