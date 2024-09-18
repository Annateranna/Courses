package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseApp()
                }
            }
        }
    }
}

@Composable
fun CourseApp(){
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 8.dp
            ),
    ){
        CoursesList(
            topicList = DataSource.topics
        )
    }
}

@Composable
fun TopicItem(topic: Topic, modifier: Modifier = Modifier) {

        Row(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEEEAF3)),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourcesId),
                modifier = Modifier
                    .height(68.dp),
                contentScale = ContentScale.Crop)
            Column(){
                Text(
                    LocalContext.current.getString(topic.stringResourcesId),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium)
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                    ){
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        modifier = Modifier.padding(start = 16.dp),
                        contentDescription = null
                    )
                    Text(
                        text = topic.numberOfCourses.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium)
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun CoursePreview() {
    CoursesTheme {
        TopicItem(Topic(R.string.architecture, 58, R.drawable.architecture))
    }
}

@Composable
fun CoursesList(topicList: List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier) {
        items(topicList) { topic ->
            TopicItem(
                topic = topic,
                modifier = Modifier.padding(8.dp)
            )

        }

    }
}