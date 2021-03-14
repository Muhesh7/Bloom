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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.Model
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.bottomList
import com.example.androiddevchallenge.ui.theme.horizontalList
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.verticalList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mode: MutableState<Boolean> = remember { mutableStateOf(false) }
            val bg = remember { mutableStateOf(R.drawable.dark_welcome_bg) }
            val img = remember { mutableStateOf(R.drawable.dark_welcome_illos) }
            val logo = remember { mutableStateOf(R.drawable.dark_logo) }
            val navController = rememberNavController()
            if (isSystemInDarkTheme()) {
                mode.value = true
                bg.value = R.drawable.dark_welcome_bg
                img.value = R.drawable.dark_welcome_illos
                logo.value = R.drawable.dark_logo
            } else {
                mode.value = false
                bg.value = R.drawable.light_welcome_bg
                img.value = R.drawable.light_welcome_illos
                logo.value = R.drawable.light_logo
            }
            MyTheme(mode.value) {
                NavHost(navController, startDestination = "Home") {
                    composable("Home") { Home(bg, img, logo, navController) }
                    composable("Login") { Login(navController) }
                    composable("Main") { Main() }
                }
            }
        }
    }
}

@Composable
fun VerticalCard(item: Model) {
    val check = remember { mutableStateOf(false) }
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp)
    ) {
        val (imgRef, textRef, descRef, checkRef, spaceRef, divideRef) = createRefs()
        Image(
            painterResource(item.img),
            "",
            Modifier
                .width(64.dp)
                .height(72.dp)
                .constrainAs(imgRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            contentScale = ContentScale.FillBounds,
        )
        Text(
            text = item.name,
            Modifier
                .paddingFromBaseline(top = 24.dp)
                .padding(horizontal = 8.dp)
                .constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(imgRef.end)
                },
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onPrimary,
        )
        Text(
            text = "This is a description",
            Modifier
                .alpha(0.7f)
                .padding(horizontal = 8.dp)
                .constrainAs(descRef) {
                    top.linkTo(textRef.bottom)
                    start.linkTo(imgRef.end)
                    bottom.linkTo(spaceRef.top)
                },
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary,
        )
        Spacer(
            modifier = Modifier
                .height(24.dp)
                .constrainAs(spaceRef) {
                    start.linkTo(imgRef.end)
                    bottom.linkTo(divideRef.bottom)
                }
        )
        Divider(
            modifier = Modifier
                .constrainAs(divideRef) {
                    start.linkTo(imgRef.end)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )
        Checkbox(
            checked = check.value,
            onCheckedChange = { check.value = !check.value },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(checkRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.secondary,
                checkmarkColor = MaterialTheme.colors.onSecondary,
                uncheckedColor = MaterialTheme.colors.onPrimary

            )
        )
    }
}

@Composable
fun HorizontalCard(item: Model) {
    Card(
        Modifier
            .padding(4.dp)
            .width(140.dp),
        elevation = 1.dp,
        shape = shapes.small
    ) {
        Column(Modifier.width(136.dp)) {
            Image(
                painterResource(item.img),
                "",
                Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = item.name,
                Modifier
                    .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    .height(26.dp)
                    .background(Color.Transparent)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun Main() {
    val search = remember { mutableStateOf("") }
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.onSecondary,
                elevation = 10.dp
            ) {
                val curRoute = remember { mutableStateOf(0) }
                val isSelected = remember { mutableStateOf(true) }

                bottomList.forEachIndexed { index, item ->
                    isSelected.value = curRoute.value == index
                    BottomNavigationItem(
                        selected = isSelected.value,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = Color.Gray,
                        label = {
                            Text(
                                text = item.name,
                            )
                        },
                        onClick = { curRoute.value = index },
                        icon = {
                            Icon(
                                item.img,
                                "",
                            )
                        }
                    )
                }
            }
        },
        content = {
            Column(Modifier.padding(horizontal = 16.dp)) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            "",
                            Modifier
                                .alpha(0.65f)
                                .padding(8.dp),
                            tint = MaterialTheme.colors.onPrimary
                        )
                    },
                    label = {
                        Text(
                            "Search",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.alpha(0.65f)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colors.onPrimary,
                        placeholderColor = MaterialTheme.colors.onPrimary,
                    ),
                    value = search.value,
                    onValueChange = { search.value = it }
                )
                Text(
                    text = "Browse themes",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .paddingFromBaseline(top = 32.dp),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(items = horizontalList) { index, item ->
                        HorizontalCard(item)
                    }
                }
                ConstraintLayout(Modifier.fillMaxWidth()) {
                    val (text, filter) = createRefs()
                    Text(
                        text = "Design your home garden",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .constrainAs(text) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                            }
                            .paddingFromBaseline(top = 40.dp),
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.h5,
                    )

                    Icon(
                        Icons.Default.FilterList,
                        "",
                        tint = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .constrainAs(filter) {
                                end.linkTo(parent.end)
                                bottom.linkTo(text.bottom)
                            }
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    itemsIndexed(items = verticalList) { index, item ->
                        VerticalCard(item)
                    }
                }
                Spacer(modifier = Modifier.padding(64.dp))
            }
        }
    )
}

@Composable
fun Login(nC: NavController) {
    val email = remember { mutableStateOf("") }
    val pw = remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Log in with email",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .paddingFromBaseline(top = 184.dp),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            label = {
                Text(
                    "Email address",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.alpha(0.65f)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onPrimary
            ),
            value = email.value,
            onValueChange = { email.value = it }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            label = {
                Text(
                    "Password (8+ Characters)",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.alpha(0.65f)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.onPrimary
            ),
            value = pw.value,
            onValueChange = { pw.value = it }
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("By clicking below you agree to our ")
                }
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Terms of Use")
                }
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(" and consent\n to our ")
                }
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Privacy Policy")
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(0.85f)
                .paddingFromBaseline(top = 24.dp),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { nC.navigate("Main") },
            border = BorderStroke(5.dp, Color.Transparent),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.secondary
            ),
            shape = shapes.medium,
            modifier = Modifier
                .height(48.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Text(
                text = "Log in",
                Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),

            )
        }
    }
}

@Composable
fun Home(
    bg: MutableState<Int>,
    img: MutableState<Int>,
    logo: MutableState<Int>,
    nC: NavController
) {

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Image(
            painterResource(id = bg.value),
            "",
            Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp)
                .background(Color.Transparent),
            contentScale = ContentScale.FillHeight
        )
        Column() {
            Spacer(modifier = Modifier.height(50.dp))
            ConstraintLayout() {
                val im = createRef()
                Image(
                    painterResource(id = img.value),
                    "",
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.40f)
                        .constrainAs(im) {
                            start.linkTo(parent.start, 88.dp)
                            end.linkTo(parent.end, (-32).dp)
                            top.linkTo(parent.top, 54.dp)
                        }
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painterResource(id = logo.value),
                "",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(88.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Beautiful home garden solutions",
                modifier = Modifier
                    .paddingFromBaseline(bottom = 32.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary,
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { /*TODO*/ },
                border = BorderStroke(5.dp, Color.Transparent),
                contentPadding = PaddingValues(16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colors.onSecondary,
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                shape = shapes.medium,
                modifier = Modifier
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = "Create account",
                    Modifier.align(Alignment.CenterVertically),

                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = { nC.navigate("Login") },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
                    .background(Color.Transparent)
            ) {
                Text(
                    text = "Log In",
                    Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}
