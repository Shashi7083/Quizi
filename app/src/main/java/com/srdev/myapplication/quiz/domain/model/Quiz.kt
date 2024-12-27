package com.srdev.myapplication.quiz.domain.model


/** this is the object that we receive in quiz response from the api
 */
data class Quiz(
    val category: String?,
    val correct_answer: String?,
    val difficulty: String?,
    val incorrect_answers: List<String?>?,
    val question: String?,
    val type: String?
)