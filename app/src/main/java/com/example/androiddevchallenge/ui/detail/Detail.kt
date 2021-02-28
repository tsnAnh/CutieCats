/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.NetworkImage
import com.example.androiddevchallenge.model.CutieCat
import com.example.androiddevchallenge.model.Gender.FEMALE
import com.example.androiddevchallenge.model.Gender.MALE
import com.example.androiddevchallenge.model.cats
import com.example.androiddevchallenge.ui.theme.purple200

@Composable
fun Detail(cutieCat: CutieCat, navigateUp: () -> Unit) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .aspectRatio(16 / 9F)
                    .fillMaxWidth()
            ) {
                NetworkImage(
                    url = cutieCat.image[0],
                    contentDescription = ""
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
                Box(modifier = Modifier.padding(8.dp)) {
                    IconButton(
                        onClick = navigateUp,
                        modifier = Modifier
                            .background(purple200, shape = CircleShape)
                            .size(40.dp)
                    ) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "")
                    }
                }
            }
        }
    ) {
        Surface(color = purple200, modifier = Modifier.fillMaxHeight()) {
            LazyColumn(state = listState) {
                item {
                    Column {
                        Column(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                            Text(cutieCat.catName, style = MaterialTheme.typography.h3)
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(
                                cutieCat.description,
                                style = MaterialTheme.typography.body1,
                                fontStyle = FontStyle.Italic
                            )
                            Spacer(modifier = Modifier.size(8.dp))
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
                            Spacer(modifier = Modifier.size(8.dp))
                            Row {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(cutieCat.eyeColor, CircleShape)
                                        .border(1.dp, shape = CircleShape, color = Color.DarkGray)
                                )
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Text("Hair color: ")
                            Spacer(modifier = Modifier.size(8.dp))
                            Row {
                                cutieCat.hairColor.forEach {
                                    Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .background(it, CircleShape)
                                            .border(
                                                1.dp,
                                                shape = CircleShape,
                                                color = Color.DarkGray
                                            )
                                            .padding(start = 8.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(
                                text = "${cutieCat.catName}'s Gallery",
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
                                        .padding(16.dp)
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
}

@Preview
@Composable
fun DetailPreview() {
    Detail(cutieCat = cats[0], navigateUp = {})
}
