package com.srdev.myapplication.quiz.presentation.quiz.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.presentation.utils.Dimens

@Composable
fun QuizOption(
    optionNumber : String,
    options : String,
    selected : Boolean,
    onOptionClick : () -> Unit,
    onUnselectOption : () -> Unit
){

    val option_stroke_color = if(selected) colorResource(id = R.color.s_option_stroke) else colorResource(id = R.color.option_stroke)
    val transition = updateTransition(selected, label = "selected")
    val icon = if(selected) painterResource(id = R.drawable.selected_option) else painterResource(id = R.drawable.unselected_option)

    val startColor by transition.animateColor (
        transitionSpec = {
            tween(durationMillis = DefaultDurationMillis , easing = LinearEasing)
        },
        label = "startColor"
    ){selectedBox ->
        if(selectedBox) colorResource(id = R.color.s_option_green)
        else {
            colorResource(id = R.color.white_bg)
        }
    }

    /**
     * yaha hm noRippleClickable custome modifier banaye hai jisse click effect show nhi hoga
     * isme hm 2 lamda function call kiye hai ek jb selected rhega tb  and dursra jb unselected rhega tb
     */
    Box(
        modifier = Modifier
            .noRippleClickable {
                if(selected){
                    onUnselectOption()
                }else{
                    onOptionClick()
                }

            }
            .fillMaxWidth()
            .height(Dimens.MediumBoxHeight)
            .clip(RoundedCornerShape(Dimens.SmallCornerRadius))
            .border(
                width = 2.dp, // Border thickness
                color = option_stroke_color, // Border color
                shape = RoundedCornerShape(Dimens.SmallCornerRadius) // Shape of the border (optional)
            )
            .background(
                color = startColor,
                shape = RoundedCornerShape(16.dp)
            )


    ){

        /**
         * this row contain A , option text and circle for user
         */
        Row (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                modifier = Modifier.weight(1.5f),
                text = optionNumber,
                fontWeight = FontWeight.Bold,
                fontSize = Dimens.MediumTextSize,
                color = colorResource(id = R.color.black),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier
                .width(Dimens.SmallSpacerWidth)
                .weight(0.6f))

            Text(
                modifier = Modifier.weight(7.1f),
                text = options ,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 3,
                color = colorResource(id = R.color.black)

            )

            // Crossfade will animate between the two icons when the selected state changes
            Box(
                modifier = Modifier
                    .weight(0.8f),
                contentAlignment = Alignment.Center
            ) {
                // Using Crossfade to animate the transition between two icons
                androidx.compose.animation.Crossfade(
                    targetState = selected,
                    animationSpec = tween(durationMillis = 500) // Custom duration for the animation
                ) { targetSelected ->
                    // Conditional resource selection based on the target state (selected/unselected)
                    val icon = if (targetSelected) {
                        painterResource(id = R.drawable.selected_option)
                    } else {
                        painterResource(id = R.drawable.unselected_option)
                    }

                    Image(
                        painter = icon,
                        contentDescription = if (targetSelected) "Selected" else "Unselected",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()

                    )
                }
            }
        }
    }


}

//to remove the ripple when we click the button
fun Modifier.noRippleClickable(onClick : () -> Unit) : Modifier = composed{
    this.clickable(
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }
    ){
        onClick()
    }
}

@Preview
@Composable
fun prv(

){
    QuizOption(optionNumber = "A", options = "jafjd jslfjsf skjfjs sjfkj", selected = false , onOptionClick = {  }) {

    }
}