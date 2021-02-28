package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.NetworkImage
import com.example.androiddevchallenge.model.CutieCat
import com.example.androiddevchallenge.model.Gender
import com.example.androiddevchallenge.model.Gender.*

@Composable
fun Detail(cutieCat: CutieCat, navigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            IconButton(onClick = navigateUp) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "")
            }
        }
    ) {
        LazyColumn {
            item {
                Column {
                    Column (
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                            ) {
                        Text(cutieCat.catName, style = MaterialTheme.typography.h3)
                        Spacer(modifier = Modifier.size(16.dp))
                        Text("Species: ${cutieCat.species}")
                        Spacer(modifier = Modifier.size(8.dp))
                        Text("Age: ${cutieCat.age}")
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            "Gender: ${
                                when (cutieCat.gender) {
                                    MALE -> "Male"
                                    FEMALE -> "Female"
                                }
                            }"
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text("Eye color: ")
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "Images of ${cutieCat.catName}",
                            style = MaterialTheme.typography.subtitle2
                        )
                    }
                    LazyRow(
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        items(cutieCat.image) {
                            Card(
                                modifier = Modifier
                                    .height(180.dp)
                                    .aspectRatio(16 / 9F)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            ) {
                                NetworkImage(
                                    url = it,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}