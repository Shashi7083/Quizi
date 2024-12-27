
import com.srdev.myapplication.quiz.domain.model.Quiz


/**
 * This is data transfer object that we receives from api get request
 * @param response_code Response Codes
 * The API appends a "Response Code" to each API Call to help tell developers what the API is doing.
 *
 * Code 0: Success Returned results successfully.
 * Code 1: No Results Could not return results. The API doesn't have enough questions for your query. (Ex. Asking for 50 Questions in a Category that only has 20.)
 * Code 2: Invalid Parameter Contains an invalid parameter. Arguements passed in aren't valid. (Ex. Amount = Five)
 * Code 3: Token Not Found Session Token does not exist.
 * Code 4: Token Empty Session Token has returned all possible questions for the specified query. Resetting the Token is necessary.
 * Code 5: Rate Limit Too many requests have occurred. Each IP can only access the API once every 5 seconds.
 *
 * @param results it contains list of data of type Quiz
 */
data class QuizResponse(
    val response_code: Int?,
    val results: List<Quiz>
)