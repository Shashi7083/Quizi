package com.srdev.myapplication.quiz.presentation.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srdev.myapplication.quiz.common.Resource
import com.srdev.myapplication.quiz.domain.model.Quiz
import com.srdev.myapplication.quiz.domain.usecases.GetQuizzesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for handling quiz-related operations and state management.
 *
 * This ViewModel uses [GetQuizzesUseCase] to fetch quizzes and updates the UI state accordingly.
 * The object creation of dependencies is managed by Dagger Hilt using the [@Inject] annotation.
 *
 * @param getQuizzesUseCase A use case to fetch quizzes from the repository.
 */
@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizzesUseCase: GetQuizzesUseCase
) : ViewModel() {

    // StateFlow to expose the current state of the screen
    private val _quizList = MutableStateFlow(QuizScreenState())
    val quizList: StateFlow<QuizScreenState> get() = _quizList

    /**
     * Handles events triggered from the UI.
     *
     * @param event An instance of [QuizScreenEvent] representing the user action.
     */
    fun onEvent(event: QuizScreenEvent) {
        when (event) {
            is QuizScreenEvent.GetQuizzes -> {
                getQuizzes(event.numOfQuizzes, event.category, event.difficulty, event.type)
            }

            is QuizScreenEvent.SetOptionSelected->{
                updateQuizStateList(event.quizStateIndex , event.selectedOption)
            }
        }
    }

    /**
     * this function update the quiz state list  (in which all questions are stored)
     * @param quizStateIndex this is question for which we have a selected option
     * @param selectedOption option that is selected by the user
     */
    private fun updateQuizStateList(quizStateIndex: Int, selectedOption: Int) {
        /**
         * Herw we create a new quizState list  that is updated
         */

        val updatedQuizStateList  = mutableListOf<QuizState>()

        /**
         * Here we iterate over every question and match quizStateIndex  and update selectedOption otherwise
         * we add that quiz as it is
         */
        quizList.value.quizState.forEachIndexed{index, quizState ->
            updatedQuizStateList.add(
                if(quizStateIndex == index){
                    quizState.copy(selectedOption = selectedOption)
                }else quizState
            )
        }

        _quizList.value = quizList.value.copy(quizState = updatedQuizStateList)

        updateScore(_quizList.value.quizState[quizStateIndex])
    }


    /**
     * Function update the score on the basic of answer selected by the user
     * @param quizState in this quiz with correct option , shuffled option , ans user selected option is
     * stored
     *
     * while showing questions ans options to user we are changing some of symbol  so  when we are checking
     * we also have to change that in options
     */
    private fun updateScore(quizState: QuizState) {

        if(quizState.selectedOption != -1){
            val correctAnswer = quizState.quiz?.correct_answer
            val selectedAnswer = quizState.selectedOption?.let {
                quizState.shuffledOptions.get(it).replace("&quot;","\"").replace("&#039", "\'")
            }
//            Log.d("check", "${correctAnswer} -> ${selectedAnswer}")
            if(correctAnswer == selectedAnswer){
                val previousScore = _quizList.value.score
                _quizList.value = _quizList.value.copy(score = previousScore + 1)
            }

        }

    }


    /**
     * Fetches quizzes using [GetQuizzesUseCase] and updates the screen state based on the result.
     *
     * @param amount The number of quizzes to fetch.
     * @param category The category ID for the quizzes.
     * @param difficulty The difficulty level of the quizzes (e.g., "easy", "medium", "hard").
     * @param type The type of quizzes (e.g., "multiple choice", "boolean").
     */
    private fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String) {
        viewModelScope.launch {
            getQuizzesUseCase(amount, category, difficulty, type).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _quizList.value = QuizScreenState(isLoading = true)
                    }

                    is Resource.Success -> {
                       val listOfQuizState : List<QuizState> = getListOfQuizState(resource.data)
                        _quizList.value = QuizScreenState(quizState = getListOfQuizState(resource.data))
                    }

                    is Resource.Error -> {
                        _quizList.value = QuizScreenState(error = resource.message ?: "Unknown error occurred")
                    }
                }
            }
        }
    }

    private fun getListOfQuizState(data: List<Quiz>?): List<QuizState> {

        val listOfQuizState = mutableListOf<QuizState>()

        for(quiz in data!!){
            val shuffledOptions = mutableListOf<String>().apply {
                add(quiz.correct_answer!!)
                addAll(quiz.incorrect_answers as Collection<String>)
                shuffle()
            }



            listOfQuizState.add(
                QuizState(
                    quiz = quiz,
                    shuffledOptions = shuffledOptions ,
                    selectedOption = -1,
                )
            )
        }
        return listOfQuizState
    }

}
