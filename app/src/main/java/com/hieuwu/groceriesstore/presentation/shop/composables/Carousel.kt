package com.hieuwu.groceriesstore.presentation.shop.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.R

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun Carousel(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        val sectionItemListState = rememberLazyListState()
        val currentVisibleItemIndex =
            remember { derivedStateOf { sectionItemListState.firstVisibleItemIndex } }
        val currentVisibleItemScrollOffset = remember {
            derivedStateOf { sectionItemListState.firstVisibleItemScrollOffset }
        }
        LaunchedEffect(currentVisibleItemIndex, currentVisibleItemScrollOffset) {
            println("currentVisibleItemIndex")
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            state = sectionItemListState
        ) {
            items(bannerImages) { image ->
                GlideImage(
                    contentScale = ContentScale.Crop,
                    model = image,
                    contentDescription = null,
                    modifier = modifier
                        .fillParentMaxWidth()
                        .animateItemPlacement()
                )
            }
        }
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            itemsIndexed(bannerImages) { curentIndex, image ->
                IndicatorDot()
            }
        }
    }
}

private val bannerImages = listOf(
    "https://firebasestorage.googleapis.com/v0/b/shopee-93233.appspot.com/o/product_image1664736648315.png?alt=media&token=2ca8be3a-37c3-4c73-b966-dcf3129958fd",
    "https://firebasestorage.googleapis.com/v0/b/shopee-93233.appspot.com/o/product_image1664737376188.png?alt=media&token=01123878-812e-4b2b-be91-d9c05b1e9b98",
    "https://firebasestorage.googleapis.com/v0/b/shopee-93233.appspot.com/o/product_image1664737421330.png?alt=media&token=66f45505-ce68-4583-b018-2d2855aa714e"
)

@Preview
@Composable
fun CarouselPreview(modifier: Modifier = Modifier) {
    Carousel(modifier = modifier.fillMaxWidth())
}

@Composable
fun IndicatorDot(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(12.dp)
            .clip(CircleShape)
            .background(colorResource(id = R.color.primary_button))
    )
}