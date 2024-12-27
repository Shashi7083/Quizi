package com.srdev.myapplication.quiz.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import com.srdev.myapplication.R
import android.os.Build
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppBar(
    quizCategory : String,
    onBackClick :  ()->Unit,
    onTaskClick : ()->Unit
){

    /**
     * getting the height of status bar so we can give it a space
     */
    // Get the view for the current activity
    val view = LocalView.current
    val context = LocalContext.current
    val density = LocalDensity.current

    // Get the window insets for status bar
    val statusBarHeightPx = view.rootWindowInsets?.isVisible(WindowInsets.Type.statusBars())?.let {
        view.rootWindowInsets?.getInsets(WindowInsets.Type.statusBars())?.top
    } ?: 0

    // Convert the height from pixels to DP
    val statusBarHeightDp = with(density) {
        statusBarHeightPx.toDp()
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(statusBarHeightDp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        colorResource(id = R.color.white).copy(alpha = 0.8f),
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                               onBackClick()
                    },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "arrow back",
                    tint = colorResource(id = R.color.black))
            }

            Text(
                text = quizCategory,
                color = Color(0xffffffff),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        colorResource(id = R.color.white).copy(alpha = 0.8f),
                        shape = CircleShape
                    )
                    .clickable {
                        onTaskClick
                    }
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "arrow back",
                    tint = colorResource(id = R.color.black))
            }
        }
    }

}

@Preview
@Composable
fun pre(){
    AppBar(quizCategory = "GK", onBackClick = {}, onTaskClick = {})
}