package com.srdev.myapplication.quiz.presentation.quiz.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.presentation.quiz.NextPrevButtonState
import com.srdev.myapplication.quiz.presentation.utils.Dimens

@Composable
fun QuizButtons(
    numberOfQuiz : Int,
    quizCategory : String,
    quizDifficulty : String,
    quiztype : String,
    qNumber : Int,
    onPreviousClick : ()->Unit,
    onNextClick : ()->Unit,
    modifier: Modifier = Modifier,
    nxtPrevButtonState : NextPrevButtonState = NextPrevButtonState(
        listOf(R.color.vivid_orange, R.color.vivid_orange),
        listOf("Previous", "Next")
    )
){




    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ) {


        /**
         * this box is for previous button
         */
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = nxtPrevButtonState.colors[0]))
                .clickable {
                        onPreviousClick()

                },

            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.4f)
                    )
                Text(
                    text = nxtPrevButtonState.text[0],
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.fixed_white)
                    )
            }
        }


        /**
         * this box is for showing current question and total questions
         */
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.vivid_orange)),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Text(
                    text = "${qNumber}/${numberOfQuiz}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = colorResource(id = R.color.fixed_white)
                )

                Text(
                    text = "Questions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.fixed_white)
                )
            }
        }


        /**
         * this box is for showing difficuilty level
         */

        // Define a function or variable to determine the difficulty color
        val difficultyColor: Color = when (quizDifficulty) {
            "Hard" -> Color.Red // You can replace with the color resource
            "Medium" -> Color.Yellow
            "Easy" -> Color.Green
            else -> Color.Gray // Default color
        }
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = R.color.vivid_orange)),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Text(
                    text = "${quizDifficulty}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = difficultyColor
                )

                Text(
                    text = "Difficuilty",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.fixed_white)
                )
            }
        }

        /**
         * this box is for going to next question
         */
        Box(
            modifier = Modifier
                .size(66.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = colorResource(id = nxtPrevButtonState.colors[1]))
                .clickable {
                    onNextClick()
                },
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.4f)
                    )
                Text(
                    text = nxtPrevButtonState.text[1],
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = colorResource(id = R.color.fixed_white)
                    )
            }
        }


    }

}

@Preview
@Composable
fun prev(){
    val pagerState = rememberPagerState {
        5
    }
    QuizButtons(
        numberOfQuiz = 15,
        quizCategory = "GK",
        quizDifficulty = "Easy",
        quiztype = "Multiple Choice",
        qNumber = 5,
        onNextClick = {},
        onPreviousClick = {},
       // modifier = Modifier.padding(bottom = 10.dp),

    )
}