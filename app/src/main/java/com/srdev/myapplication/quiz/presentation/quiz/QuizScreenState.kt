package com.srdev.myapplication.quiz.presentation.quiz

import com.srdev.myapplication.quiz.domain.model.Quiz

data class QuizScreenState (
    val isLoading : Boolean = false,
    val quizState : List<QuizState>  = emptyList(),
    val error : String = "",
    val score : Int = 0
)

data class QuizState(
    val quiz : Quiz? = null,
    val shuffledOptions : List<String>  = emptyList(),
    val selectedOption : Int ? = -1,

)

data class NextPrevButtonState(
    val colors : List<Int>,
    val text : List<String>
)