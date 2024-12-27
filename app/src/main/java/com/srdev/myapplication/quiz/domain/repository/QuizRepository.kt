package com.srdev.myapplication.quiz.domain.repository

import com.srdev.myapplication.quiz.domain.model.Quiz

/**
 * The QuizRepository interface defines methods to retrieve quiz data.
 *
 * This interface belongs to the domain layer because:
 * 1. **Business Logic Layer**: It abstracts the logic related to accessing quizzes, making it easier to modify how data is fetched (e.g., change data sources) without affecting other parts of the app.
 * 2. **Decoupling**: It decouples the application's business logic from the implementation details, such as whether the data comes from a network request, a local database, or an external API.
 * 3. **Testability**: By using an interface, it enables easier unit testing by allowing mock implementations of this repository.
 * 4. **Separation of Concerns**: The domain layer is focused on defining the problem space (i.e., the core business operations), while the implementation details of how quizzes are fetched (e.g., from a REST API, a local database) are left to the data layer (the repository implementation).
 */
interface QuizRepository {

    /**
     * Fetches a list of quizzes based on the provided parameters.
     *
     * @param amount The number of quizzes to retrieve.
     * @param category The category of quizzes.
     * @param difficulty The difficulty level of the quizzes (e.g., easy, medium, hard).
     * @param type The type of quiz (e.g., multiple-choice).
     *
     * @return A list of quizzes that match the specified parameters.
     */
    suspend fun getQuizzes(amount: Int, category: Int, difficulty: String, type: String): List<Quiz>
}
