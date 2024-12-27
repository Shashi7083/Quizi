package com.srdev.myapplication.quiz.presentation.navGraph


const val ARG_KEY_Quiz_NUMBER = "ak_quiz_number"
const val ARG_KEY_Quiz_CATEGORY = "ak_quiz_category"
const val ARG_KEY_Quiz_DIFFICULTY = "ak_quiz_difficulty"
const val ARG_KEY_Quiz_type = "ak_quiz_type"
const val NOQ_KEY = "num_of_question_key"
const val CORRECT_ANS_KEY = "correct_answer"


sealed class Routes(
    val route : String
) {

    object HomeScreen : Routes("home_screen")

    object QuizScreen : Routes("quiz_screen/{$ARG_KEY_Quiz_NUMBER}/{$ARG_KEY_Quiz_CATEGORY}/{$ARG_KEY_Quiz_DIFFICULTY}/{$ARG_KEY_Quiz_type}"){

        fun passQuizParams(numOfQuizzes : Int , category : String , difficulty : String, type : String) : String {

            return  "quiz_screen/{$ARG_KEY_Quiz_NUMBER}/{$ARG_KEY_Quiz_CATEGORY}/{$ARG_KEY_Quiz_DIFFICULTY}/{$ARG_KEY_Quiz_type}"
                .replace(
                    oldValue = "{$ARG_KEY_Quiz_NUMBER}",
                    newValue = numOfQuizzes.toString()
                )

                .replace(
                    oldValue = "{$ARG_KEY_Quiz_CATEGORY}",
                    newValue = category.toString()
                )

                .replace(
                    oldValue = "{$ARG_KEY_Quiz_DIFFICULTY}",
                    newValue = difficulty.toString()
                )

                .replace(
                    oldValue = "{$ARG_KEY_Quiz_type}",
                    newValue = type.toString()
                )
        }
    }

    object ScoreScreen : Routes("score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"){

        fun passNumOfQuestionsAndCorrectAns(questions : Int, correctAnswers : Int) : String{
            return "score_screen/{$NOQ_KEY}/{$CORRECT_ANS_KEY}"
                .replace("{$NOQ_KEY}", questions.toString())
                .replace("{$CORRECT_ANS_KEY}", correctAnswers.toString())
        }
    }
}