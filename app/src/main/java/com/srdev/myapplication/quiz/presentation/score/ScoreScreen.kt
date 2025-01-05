package com.srdev.myapplication.quiz.presentation.score

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.view.View
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.presentation.common.AppBar
import com.srdev.myapplication.quiz.presentation.common.BackHandlerWithConfirmation
import com.srdev.myapplication.quiz.presentation.navGraph.Routes
import com.srdev.myapplication.quiz.presentation.quiz.component.noRippleClickable
import java.text.DecimalFormat

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ScoreScreen(
    numOfQuestions : Int,
    numOfCorrectAns : Int,
    navController: NavController
){




    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            colorResource(id = R.color.top_bg), // Start color
            colorResource(id = R.color.bottom_bg) // End color
        )
    )

    //This is to check user shared the score or not
    val sharedOnSocialMedia = remember {
        mutableStateOf(
            false
        )
    }
    //this is to check dialog show
    var showDialog  = remember{ mutableStateOf(false)}

    //OnBAck Pressed System
    BackHandler {
        if(sharedOnSocialMedia.value){
            goToHome(navController = navController)
        }else{
            showDialog.value = true
        }

    }


    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = colorResource(id = R.color.black))){
            append("You attempted ")
        }

        withStyle(style = SpanStyle(color = Color.Blue)){
            append("${numOfQuestions} questions ")
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.black))){
            append("and from that ")
        }
        withStyle(style = SpanStyle(color = colorResource(id = R.color.green))){
            append("${numOfCorrectAns} answers ")
        }

        withStyle(style = SpanStyle(color = colorResource(id = R.color.black))){
            append("are correct")
        }
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)

    ){
        Image(
            painter = painterResource(id = R.drawable.elephant),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            alpha = 1f
        )

        // this box is blured so we put anything inside it ,is blured
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
                .blur(
                    radiusX = 5.dp,
                    radiusY = 5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Rectangle
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AppBar(
                quizCategory = "",
                onTaskClick = {

                },
                onBackClick = {
                    if(sharedOnSocialMedia.value){
                        goToHome(navController = navController)
                    }else{
                        showDialog.value = true
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 25.dp)
                    .background(
                        color = colorResource(id = R.color.dark_top_bg),
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ){

                //Row for the showing total total and correct answers
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .background(
                            color = colorResource(id = R.color.dark_top_bg),
                            shape = RoundedCornerShape(
                                topStart = 30.dp,
                                topEnd = 30.dp,
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total Questions : ${ numOfQuestions}",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.fixed_white)
                        ),
                    )
                    Text(
                        text = "Correct Answer : ${numOfCorrectAns}",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.fixed_white)
                        ),
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        //.padding(top = 30.dp)
                        .background(
                            color = colorResource(id = R.color.white_bg),
                            shape = RoundedCornerShape(
                                topStart = 30.dp,
                                topEnd = 30.dp,
                                bottomEnd = 15.dp,
                                bottomStart = 15.dp
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                        ScoreCard(
                            noOfQuestions = numOfQuestions,
                            correctAnswer = numOfCorrectAns
                        )


                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "Quiz completed successfully",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.black)
                        )



                        Text(
                            text = annotatedString,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )

                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "Share with us :",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.width(20.dp))

                            //whatsapp instagram and facebook
                            Row(
                                modifier = Modifier.noRippleClickable {
                                                              sharedOnSocialMedia.value = true
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(20.dp)

                            ) {


                                Image(
                                    painter = painterResource(id = R.drawable.whatsapp),
                                    contentDescription = "",
                                    modifier = Modifier.size(25.dp)
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.facebook),
                                    contentDescription = "",
                                    modifier = Modifier.size(25.dp)
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.instagram),
                                    contentDescription = "",
                                    modifier = Modifier.size(25.dp)
                                )
                            }



                        }

                        Button(
                            onClick ={
                                //navigate to Home Screen

                                if(sharedOnSocialMedia.value){
                                    goToHome(navController = navController)
                                }else{
                                    showDialog.value = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .background(
                                    color = colorResource(id = R.color.vivid_orange),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.vivid_orange) // Set the background color of the button
                            )
                        ) {
                            Text(
                                text = "Explore More Quizzes, Create more",
                                color = colorResource(id = R.color.fixed_white)
                            )
                        }
                    }

                }
            }
        }



    }

    if(showDialog.value){
        BackHandlerWithConfirmation (
            title = "Share Score",
            text = "Are you sure, you don't want to share your achivements ?",
            confirmText = "Go to home",
            dismissText = "Share",
            onExit = {
                goToHome(navController = navController)

            },
            onStay = {
                showDialog.value = false
            }
        )
    }
}


@Composable
fun ScoreCard(
    noOfQuestions : Int,
    correctAnswer : Int
){

    // Lottie animation composition from a JSON file or URL
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.star)) // Replace with your Lottie file in res/raw
    // Play animation infinitely
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    val scorePercentage = calculatePercentage(correctAnswer, noOfQuestions)

    ConstraintLayout(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 60.dp)
            .fillMaxWidth(0.8f)
            .height(390.dp)
            //.fillMaxHeight(0.f)


    ) {
        val (element1, element2) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(element1) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            contentAlignment = Alignment.Center
            ){

            Image(
                painter = painterResource(id = R.drawable.score_bg),
                contentDescription ="",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()

            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp, start = 25.dp, end = 25.dp, bottom = 90.dp)

                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Text(
                    text = "Congratulations",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )

                Text(
                    text = "SCORE",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )
                )

                Text(
                    text = "${scorePercentage.toString()}%",
                    style = TextStyle(
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
            }

            }


        Box(
            modifier = Modifier
                .constrainAs(element2) {
                    top.linkTo(parent.top, margin = -15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
                .size(150.dp)
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp))
                .background(colorResource(id = R.color.white_bg), shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ){
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.fillMaxSize() // Adjust the size of the animation
            )
        }
        }
}


/**
 * k is correct answer
 * n is total questions
 */
fun calculatePercentage(k : Int, n : Int) : Double{
    require( k >= 0 && n >0){
        "Invalid input: k must be non-negative and n must be positive"
    }
    val percentage = (k.toDouble()/n.toDouble()) * 100.0
    return DecimalFormat("#.##").format(percentage).toDouble()
}

fun goToHome( navController: NavController){
    navController.navigate(Routes.HomeScreen.route){
        popUpTo(Routes.HomeScreen.route){
            inclusive = true
        }
    }
}




@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun p(
    name: String = "Night",
    uiMode: Int = Configuration.UI_MODE_NIGHT_YES
){
    ScoreScreen(
        numOfQuestions = 16,
        numOfCorrectAns = 3,
        navController = rememberNavController()
    )
}