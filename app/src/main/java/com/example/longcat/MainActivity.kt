package com.example.longcat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.longcat.ui.theme.LongCatTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val photos = ArrayList<Int>()
        photos.add(R.drawable.cube)
        photos.add(R.drawable.cat)
        photos.add(R.drawable.brick)
        photos.add(R.drawable.transparent)
        photos.add(R.drawable.cheetah)
        setContent {
            LongCatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                    PhotoGrid(photos = photos)
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "lOng cAt!",
        )
    }
}

@Composable
fun PhotoGrid(photos: List<Int>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = Modifier
            .padding(top = 100.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
            .background(Color.Gray)
    ) {
        items(36) {
            Log.d("Shashank", it.toString())
            when (it) {
                3,4 -> {
                    //traversed
                    PhotoItem(image = photos[4])
                }
                2 -> {
                    //current position
                    PhotoItem(image =  photos[1])
                }
                0,1,5, 30, 21 -> {
                    //blocked bricks
                    PhotoItem(image =  photos[2])
                }
                else -> {
                    //not Traversed
                    PhotoItem(image =  photos[3])
                }
            }
        }
    }
}

@Composable
fun PhotoItem(image: Int){
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var modifier =Modifier
        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        .draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState { delta ->
                offsetX += delta
            }
        )
        .fillMaxSize()
        .padding(vertical = 0.dp, horizontal = 0.dp)
    if(image == R.drawable.transparent ){
        modifier = Modifier
            .fillMaxSize()
            .alpha(0f)
            .padding(vertical = 0.dp, horizontal = 0.dp)
    }
    Image(painter = painterResource(id = image), contentDescription = "photo",modifier = modifier, contentScale = ContentScale.Inside)
}