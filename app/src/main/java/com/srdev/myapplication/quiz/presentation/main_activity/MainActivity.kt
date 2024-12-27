package com.srdev.myapplication.quiz.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.srdev.myapplication.R
import com.srdev.myapplication.quiz.presentation.home.HomeScreen
import com.srdev.myapplication.quiz.presentation.home.HomeViewModel
import com.srdev.myapplication.quiz.presentation.navGraph.SetNavGraph
import com.srdev.myapplication.ui.theme.QuiziTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            QuiziTheme {

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.mid_night_blue)),
                    contentAlignment = Alignment.Center
                ){

                    SetNavGraph()
                }
            }
        }
    }
}

