package com.srdev.myapplication.quiz.domain.usecases

import com.srdev.myapplication.quiz.common.Resource
import com.srdev.myapplication.quiz.domain.model.Quiz
import com.srdev.myapplication.quiz.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


/**
 * Hm QuizRepositoryImpl ko directly viewModel me use kr skte hai pr usecase create krte hai taki code maintainable and testable reh ske
 * isme hm ye dekhenge ki quiz load hua hai ya nhi ya error hai
 * isko observ kareng viewmodel pe isliye return type Flow rakhenge
 * Use Case for retrieving quizzes.
 *
 * The purpose of a use case is to make the code maintainable, modular, and testable.
 * This class interacts with the [QuizRepository] to fetch quizzes and handles the result,
 * emitting a [Resource] object to represent different states like Loading, Success, or Error.
 *
 * The returned type is a [Flow] because the data is observed asynchronously,
 * making it efficient for operations like observing in the ViewModel.
 *
 * @param quizRepository The repository interface that fetches quiz data.
 * This object is injected by Dagger Hilt.
 */
class GetQuizzesUseCase(
    private val quizRepository: QuizRepository
) {

    /**
     * Fetches quizzes based on the provided parameters.
     *
     * @param amount The number of quizzes to fetch.
     * @param category The category ID for the quizzes.
     * @param difficulty The difficulty level of the quizzes (e.g., "easy", "medium", "hard").
     * @param type The type of quizzes (e.g., "multiple choice", "boolean").
     *
     * @return A [Flow] of [Resource] containing a list of [Quiz] objects.
     * The [Resource] indicates whether the data is loading, successfully fetched, or an error occurred.
     */
//    fun getQuizzes(
    /**
     * agar hm operator fun invoke likhte hai to GetQuizzesUseCase ka instance hi function bn jayega
     * .invoke() krne ka jarurat nhi padega  (ye isliye kiye kyoki iska code change nhi hoga aur hr use case ke liye alg file banate hai)
     */
    //    fun getQuizzes(
    operator fun invoke(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): Flow<Resource<List<Quiz>>> = flow {
        // Emit a loading state to indicate the request is in progress
        emit(Resource.Loading())

        try {
            // Fetch quizzes from the repository and emit the success state with data
            val quizzes = quizRepository.getQuizzes(amount, category, difficulty, type)
            emit(Resource.Success(data = quizzes))
        } catch (e: Exception) {
            // Emit an error state with the exception message
            emit(Resource.Error(message = e.message ?: "An unknown error occurred"))
        }
    }.flowOn(Dispatchers.IO) // Ensure the flow runs on the IO dispatcher for background work
}