package com.srdev.myapplication.quiz.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.presentation.utils.Dimens

@Composable
fun HomeHeader(){

    Box(
        modifier = Modifier
            .fillMaxWidth()
           // .height(Dimens.HomeTopBoxHeight)
           // .background(color = Color.Red)
            .clip(RoundedCornerShape(
                bottomStart = Dimens.ExtraLargeCornerRadius,
                bottomEnd = Dimens.ExtraLargeCornerRadius
            )),
        contentAlignment = Alignment.TopCenter
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.SmallPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_open_24),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.MediumIconSize),
                tint = colorResource(id = R.color.blue_grey)
            )

            Text(
                text = "Quiz Options",
                color = colorResource(id = R.color.blue_grey),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .weight(3.5f),
                textAlign = TextAlign.Center,
                fontSize = Dimens.LargeText
            )

            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "person",
                modifier = Modifier
                    .weight(1f)
                    .size(Dimens.MediumIconSize),
                tint = colorResource(id = R.color.blue_grey)
                )
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun preview(){
    HomeHeader()
}