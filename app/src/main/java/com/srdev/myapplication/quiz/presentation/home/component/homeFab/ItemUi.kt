package com.srdev.myapplication.quiz.presentation.home.component.homeFab

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.srdev.myapplication.R

@Composable
fun ItemUi(
    icon :Painter,
    title :String
){
    
    val context = LocalContext.current


        Box(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.dark_top_bg),
                    shape = RoundedCornerShape(30.dp)
                ),
            contentAlignment = Alignment.CenterEnd
        ) {
                Row (
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ){


                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = title,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.fixed_white)
                        )
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    FloatingActionButton(
                        onClick = {
                            Toast.makeText(context,title,Toast.LENGTH_SHORT).show()
                        },
                        containerColor = colorResource(id = R.color.fixed_white),
                        modifier = Modifier.size(45.dp)
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp),
                            tint = colorResource(id = R.color.vivid_orange))
                    }
                }
        }

}

@Preview
@Composable
fun prv(){
    ItemUi(icon = painterResource(id = R.drawable.ai_chat), title = "")
}