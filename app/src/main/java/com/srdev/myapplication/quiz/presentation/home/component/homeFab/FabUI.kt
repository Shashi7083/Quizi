package com.srdev.myapplication.quiz.presentation.home.component.homeFab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srdev.myapplication.R


/**
 * Fab Ui is for the main fab button that contain some sub-fab button inside it
 * with animation
 */
@Composable
fun FabUI(){

    /**
     * this expanded checks for that all buttons are showing or not
     */
    var expanded by remember {
        mutableStateOf(false)
    }

    /**
     * Set Animation for opening and closing , if this is expanded the we rotate our main floating button
     * else set to normal
     */
    val transition = updateTransition(targetState = expanded, label = "transition")
    val rotation by transition.animateFloat(label = "rotation"){
        if(it) 360f else 0f
    }

    /**
     * this is to change fab bg when clicked
     */
    val fabBg by transition.animateColor (
        transitionSpec = {
            tween(durationMillis = AnimationConstants.DefaultDurationMillis , easing = LinearEasing)
        },
        label = "startColor"
    ){
        if(it) {
            colorResource(id = R.color.vivid_orange)
        }
        else {
            colorResource(id = R.color.blue_grey)
        }
    }

    val fabIcon = if(expanded) painterResource(id = R.drawable.close) else painterResource(id = R.drawable.ai_fab)

    val items = listOf(
        MiniFabItems(painterResource(id = R.drawable.create_with_ai), "Create with Ai"),
        MiniFabItems(painterResource(id = R.drawable.ai_chat), "Ai Chat"),
        MiniFabItems(painterResource(id = R.drawable.ai_setting), "Settings"),
    )


    Column(
        modifier =  Modifier,
        horizontalAlignment = Alignment.End
    ) {


        /**
         * Here lazy column for the sub-fab button
         * create animation
         */

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = {it})+ expandVertically (),
            exit = fadeOut() + slideOutVertically (targetOffsetY = {it})+ shrinkVertically ()
            ) {

            LazyColumn(
                modifier = Modifier.padding(bottom = 10.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items.size){
                    ItemUi(icon = items[it].icon, title = items[it].title)
                }
            }

        }



        /**
         * On this button click we close if open and open sub menu if closed
         */
        FloatingActionButton(
            onClick = {
                expanded = !expanded
            },
            modifier = Modifier.rotate(rotation),
            containerColor = fabBg
        ) {
            Icon(
                painter = fabIcon,
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp),
                tint = colorResource(id = R.color.fixed_black)
            )
        }
    }
}

@Composable
@Preview
fun prvw(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FabUI()
        }
    ) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(it)

       ) {
           
       }
    }

}