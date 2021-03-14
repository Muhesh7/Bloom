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
package com.example.androiddevchallenge.ui.theme

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.androiddevchallenge.R

// Set of Material typography styles to start with

private val appFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.nunitosans_bold,
            weight = FontWeight.W900,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.nunitosans_semibold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.nunitosans_light,
            weight = FontWeight.W900,
            style = FontStyle.Italic
        ),

    )
)

private val defaultTypography = Typography()
val typography = Typography(
    h1 = defaultTypography.h1.copy(fontFamily = appFontFamily),
    h2 = defaultTypography.h2.copy(fontFamily = appFontFamily),
    h3 = defaultTypography.h3.copy(fontFamily = appFontFamily),
    h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
    h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
    h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = appFontFamily),
    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
    body1 = defaultTypography.body1.copy(fontFamily = appFontFamily),
    body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
    button = defaultTypography.button.copy(fontFamily = appFontFamily),
    caption = defaultTypography.caption.copy(fontFamily = appFontFamily),
    overline = defaultTypography.overline.copy(fontFamily = appFontFamily)
)

data class Model(
    val name: String,
    val img: Int
)

data class ModelB(
    val name: String,
    val img: ImageVector
)
val bottomList = arrayListOf(
    ModelB(
        "Home",
        Icons.Default.Home
    ),
    ModelB(
        "Favorites",
        Icons.Default.FavoriteBorder
    ),
    ModelB(
        "Profile",
        Icons.Default.AccountCircle
    ),
    ModelB(
        "Cart",
        Icons.Default.ShoppingCart
    )
)

val horizontalList = arrayListOf(
    Model(
        "Desert chic",
        R.drawable.pexels_quang_nguyen_vinh_2132227
    ),
    Model(
        "Tiny terranriums",
        R.drawable.pexels_katarzyna_modrzejewska_1400375
    ),
    Model(
        "Jungle vibes",
        R.drawable.pexels_volkan_vardar_5699665
    ),
    Model(
        "Easy care",
        R.drawable.pexels_ad_ad_6208086
    ),
    Model(
        "Statements",
        R.drawable.pexels_sidnei_maia_3511755
    ),
)

val verticalList = arrayListOf(
    Model(
        "Monstera",
        R.drawable.pexels_huy_phan_3097770
    ),
    Model(
        "Aglaonema",
        R.drawable.pexels_karolina_grabowska_4751978
    ),
    Model(
        "Peace lily",
        R.drawable.pexels_melvin_vito_4425201
    ),
    Model(
        "Fiddle leaf tree",
        R.drawable.pexels_ds_ds_6208087
    ),
    Model(
        "Snake plant",
        R.drawable.pexels_fabian_stroobants_2123482
    ),
    Model(
        "Pothos",
        R.drawable.pexels_faraz_ahmad_1084199
    ),
)

@Composable
fun glider(
    @DrawableRes default: Int
): MutableState<Bitmap?> {

    val bitmap: MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(default)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                bitmap.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    return bitmap
}
