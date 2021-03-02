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
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.remote.Data
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.gson.Gson
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(onButtonClick = {
                    openDetail(it)
                })
            }
        }
    }

    private fun openDetail(item: Dog) {
        val intent = Intent(baseContext, DetailActivity::class.java)
        intent.putExtra("name", item.name)
        intent.putExtra("desc", item.desc)
        intent.putExtra("photoUrl", item.photoUrl)
        startActivity(intent)
    }
}

// Start building your app here!
@Composable
fun MyApp(onButtonClick: (item: Dog) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        val fromJson = Gson().fromJson(Data.data, Dogs::class.java)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(fromJson.dogs) { item ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            onButtonClick(item)
                        },
                ) {
                    GlideImage(
                        data = item.photoUrl,
                        contentDescription = null
                    )

                    Spacer(Modifier.height(16.dp))

                    Text("Name : " + item.name + ", Age : " + item.age + ", Sex : " + item.sex)
                }
            }
        }
    }
}