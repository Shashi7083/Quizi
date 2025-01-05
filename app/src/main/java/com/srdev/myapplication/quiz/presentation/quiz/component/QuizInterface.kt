package com.srdev.myapplication.quiz.presentation.quiz.component

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.presentation.quiz.QuizState
import com.srdev.myapplication.quiz.presentation.utils.Dimens

/**
 * Quiz Interface is for the question and options
 * @param onOptionSelected this lamda function return option selected , where this QuizInterface called
 * @param qNumber  number of question displaying
 * @param modifier yha hm calling function or screen se kuch modifier parameter lga ke paas kr skte
 */
@Composable
fun QuizInterface(
    onOptionSelected:(Int) -> Unit,
    qNumber : Int,
    quizState: QuizState,
    modifier : Modifier = Modifier,

){

    val question : String = quizState.quiz?.question!!.replace("&quot;","\"").replace("&#039", "\'")

    //Every content will display on this Column
    Column(
        modifier = modifier
//            .fillMaxSize()
            ,

    ){

        /**
         * this box contain question  and background  and scrolable column for question
         */
        Box(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ){

            /**
             * this is the background image for the box in which question will display
             */
                Image(
                    painter = painterResource(id = R.drawable.question_bg),
                    contentDescription = "Background Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 1f
                )

            /**
             * Column that contain scrolable property if the question is too long
             *
             */
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier

                        .fillMaxSize() // Ensures the column respects the parent's size
                        .verticalScroll(rememberScrollState()) // Adds scrolling for overflow
                ) {

                    /**
                     * IN this text we will give question that user see
                     */
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        text = question,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.fixed_white)
                        ),

                    )
                }

            }

        Spacer(modifier = Modifier.height(Dimens.LargeSpacerHeight))

        /**
         * Here is the options for the question
         */

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
//                .fillMaxSize(),
            ,verticalArrangement = Arrangement.SpaceBetween
        ) {
            var options = listOf(
                "A" to quizState.shuffledOptions[0].replace("&quot;","\"").replace("&#039", "\'"),
                "B" to quizState.shuffledOptions[1].replace("&quot;","\"").replace("&#039", "\'"),
            )

            if(quizState.shuffledOptions.size <=2){
                 options = listOf(
                    "A" to quizState.shuffledOptions[0].replace("&quot;","\"").replace("&#039", "\'"),
                    "B" to quizState.shuffledOptions[1].replace("&quot;","\"").replace("&#039", "\'"),

                )
            } else{
                 options = listOf(
                    "A" to quizState.shuffledOptions[0].replace("&quot;","\"").replace("&#039", "\'"),
                    "B" to quizState.shuffledOptions[1].replace("&quot;","\"").replace("&#039", "\'"),
                    "C" to quizState.shuffledOptions[2].replace("&quot;","\"").replace("&#039", "\'"),
                    "D" to quizState.shuffledOptions[3].replace("&quot;","\"").replace("&#039", "\'")
                )
            }


            Column {
                options.forEachIndexed{ index, (optionNumber , optionText) ->
                    if(optionText.isNotEmpty()){
                        QuizOption(
                            optionNumber = optionNumber,
                            options = optionText,
                            selected = if(quizState.selectedOption == index) true else false,
                            onOptionClick = {onOptionSelected(index) },
                            onUnselectOption = {onOptionSelected(-1)}
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimens.MediumSpacerHeight))
                }


            }


        }
    }

    
}


/**
 * to preview ui in android studio
 */

//@Preview
//@Composable
//fun preview(
//){
//    QuizInterface(
//        onOptionSelected = {},
//        qNumber = 1,
//    )
//}
//
