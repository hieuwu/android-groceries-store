package com.hieuwu.groceriesstore.presentation.shop.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun Carousel(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        val sectionItemListState = rememberLazyListState()
        val currentVisibleIndex = remember { mutableStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {

            LazyRow(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(180.dp),
                state = sectionItemListState,
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

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(42.dp)
                        .background(colorResource(id = R.color.primary_button))
                        .clickable {
                            coroutineScope.launch {
                                if (currentVisibleIndex.value != 0) {
                                    currentVisibleIndex.value -= 1
                                    sectionItemListState.animateScrollToItem(currentVisibleIndex.value)
                                } else {
                                    currentVisibleIndex.value = bannerImages.size - 1
                                    sectionItemListState.animateScrollToItem(currentVisibleIndex.value)
                                }
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        modifier = modifier.align(Alignment.Center),
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(42.dp)
                        .background(colorResource(id = R.color.primary_button)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = modifier
                            .align(Alignment.Center)
                            .clickable {
                                coroutineScope.launch {
                                    if (currentVisibleIndex.value == bannerImages.size - 1) {
                                        currentVisibleIndex.value = 0
                                        sectionItemListState.animateScrollToItem(currentVisibleIndex.value)
                                    } else {
                                        currentVisibleIndex.value += 1
                                        sectionItemListState.animateScrollToItem(currentVisibleIndex.value)
                                    }
                                }
                            },
                        imageVector = Icons.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            itemsIndexed(bannerImages) { curentIndex, image ->
                val currentlyVisibleIndex = (sectionItemListState.layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0)
                IndicatorDot(isSelected = currentlyVisibleIndex== curentIndex)
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
fun IndicatorDot(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(12.dp)
            .clip(CircleShape)
            .background(
                colorResource(id = if (isSelected) R.color.primary_button else R.color.light_gray)
            )
    )
}