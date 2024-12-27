package com.srdev.myapplication.quiz.data.repository

import com.srdev.myapplication.quiz.domain.model.Quiz
import com.srdev.myapplication.quiz.domain.repository.QuizRepository
import com.srdev.myapplication.quiz.data.remote.QuizApi // Example of using a remote API

/**
 * The QuizRepositoryImpl class implements the QuizRepository interface defined in the domain layer.
 *
 * This class belongs to the data layer because:
 * 1. **Concrete Implementation**: It provides the actual implementation of the methods defined in the domain layer repository interface.
 * 2. **Data Access Logic**: It contains the logic to fetch the data from an actual data source, such as a network API or local database.
 * 3. **Separation of Concerns**: The data layer handles all the implementation details regarding data fetching, while the domain layer remains decoupled from these details.
 */

class QuizRepositoryImpl(private val quizApi: QuizApi) : QuizRepository {

    /**
     * Fetches quizzes from a remote API or other data sources.
     *
     * @param amount The number of quizzes to retrieve.
     * @param category The category of quizzes.
     * @param difficulty The difficulty level of the quizzes (e.g., easy, medium, hard).
     * @param type The type of quiz (e.g., multiple-choice).
     *
     * @return A list of quizzes fetched from the data source.
     */

    // yaha hamara quizApi.getQuizzes return krta hai quizResponse ki jisme code and list dono hai
    //yaha hm bs list return karenge
    override suspend fun getQuizzes(
        amount: Int,
        category: Int,
        difficulty: String,
        type: String
    ): List<Quiz> {
         return quizApi.getQuizzes(amount, category, difficulty, type).results
    }
}
