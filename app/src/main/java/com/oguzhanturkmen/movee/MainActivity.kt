package com.oguzhanturkmen.movee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.oguzhanturkmen.movee.presentation.Navigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // HorizontalPagerWithOffsetTransition()
            Navigation()
            //gradiant()
            //SnappyLazyRow()
            //horizontalPager()

        }
    }
}


