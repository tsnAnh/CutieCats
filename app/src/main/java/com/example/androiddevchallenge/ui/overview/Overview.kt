package com.example.androiddevchallenge.ui.overview

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.NetworkImage
import com.example.androiddevchallenge.model.CutieCat
import com.example.androiddevchallenge.model.cats
import com.example.androiddevchallenge.ui.theme.purple200
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Overview(navigateToDetails: (CutieCat) -> Unit) {
    CutieCatList(navigateToDetails = navigateToDetails)
}

@Composable
fun CutieCatList(modifier: Modifier = Modifier, navigateToDetails: (CutieCat) -> Unit) {
    LazyColumn(modifier = modifier.background(purple200)) {
        item {
            Column {
                Box(
                    modifier = Modifier
                        .aspectRatio(16 / 9F)
                        .fillMaxWidth()
                ) {
                    NetworkImage(
                        url = "https://static.wikia.nocookie.net/creamheroes/images/6/6e/DD_Pose_2.jpeg",
                        contentDescription = "https://static.wikia.nocookie.net/creamheroes/images/6/6e/DD_Pose_2.jpeg"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, purple200),
                                )
                            )
                    ) {

                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 96.dp, bottom = 8.dp)
                    ) {
                        Text("Cutie Cats", style = MaterialTheme.typography.h4)
                        Text("Adopt a cat!", style = MaterialTheme.typography.subtitle1)
                    }
                }
                Text(
                    "Cats need adoption",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items(cats) { cat ->
            CutieCatItem(cat, navigateToDetails)
        }
    }
}

@Composable
fun CutieCatItem(cutieCat: CutieCat, onItemClick: (CutieCat) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(8.dp)
            .clickable { onItemClick(cutieCat) },
        shape = MaterialTheme.shapes.large.copy(CornerSize(24.dp))
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(cutieCat.catName, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.size(8.dp))
                Text("Age: ${cutieCat.age}")
                Spacer(modifier = Modifier.size(16.dp))
                Row {
                    Text("Hair color: ", style = MaterialTheme.typography.caption)
                    Spacer(modifier = Modifier.size(8.dp))
                    cutieCat.hairColor.forEach { color ->
                        Box(
                            modifier = Modifier.size(16.dp)
                                .background(color, CircleShape)
                                .border(1.dp, Color.DarkGray, CircleShape)
                        ) { }
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
            Card(
                shape = MaterialTheme.shapes.large.copy(
                    topStart = CornerSize(24.dp),
                    bottomEnd = CornerSize(24.dp)
                )
            ) {
                NetworkImage(
                    url = cutieCat.image[0],
                    contentDescription = "",
                    modifier = Modifier.size(140.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CutieCatItemPreview() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp),
        shape = MaterialTheme.shapes.large.copy(CornerSize(24.dp))
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("DD", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.size(8.dp))
                Text("Species: Northwest", style = MaterialTheme.typography.caption)
            }
            Card(
                shape = MaterialTheme.shapes.large.copy(
                    topStart = CornerSize(24.dp),
                    bottomEnd = CornerSize(24.dp)
                )
            ) {
                NetworkImage(
                    url = "https://static.wikia.nocookie.net/creamheroes/images/6/6e/DD_Pose_2.jpeg",
                    contentDescription = "",
                    modifier = Modifier.size(180.dp)
                )
            }
        }
    }
}
