package com.hieuwu.groceriesstore.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.CategoryModel
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.presentation.explore.composables.CategoryItem
import com.hieuwu.groceriesstore.presentation.explore.composables.ProductItem
import kotlinx.coroutines.launch

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    navigateToProductList: (CategoryModel) -> Unit,
    navigateToProductDetail: (ProductModel) -> Unit,
) {

    val searchName by viewModel.searchString.collectAsState()
    val categoryList by viewModel.categories.collectAsState()
    val productList by viewModel.productList.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SearchBarSection(
                searchName = searchName,
                onSearchNameChange = viewModel::searchNameChanged,
                onSearchClick = {
                    viewModel.searchProduct(searchName)
                },
                onClearInput = viewModel::clearInput
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->

        ProductListSection(
            modifier = Modifier.padding(contentPadding),
            productList = productList,
            categoryList = categoryList,
            navigateToProductDetail = navigateToProductDetail,
            navigateToProductList = navigateToProductList,
            onAddToCartClick = { product ->
                viewModel.addToCart(product)
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Added ${product.name}"
                    )
                }
            }
        )
    }

}

@Composable
fun ProductListSection(
    modifier: Modifier = Modifier,
    categoryList: List<CategoryModel>?,
    productList: List<ProductModel>?,
    navigateToProductDetail: (ProductModel) -> Unit,
    navigateToProductList: (CategoryModel) -> Unit,
    onAddToCartClick: (ProductModel) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        if (productList == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                val composition by rememberLottieComposition(
                    LottieCompositionSpec.Asset("explore_animation.json")
                )
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(200.dp),
                    iterations = LottieConstants.IterateForever
                )
            }
        } 
        else {

            Column {
                Text(
                    text = "Categories",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                LazyRow {
                    categoryList?.let {

                        items(it) { category ->
                            CategoryItem(
                                modifier = Modifier.padding(8.dp),
                                category = category,
                                onItemClick = {
                                    navigateToProductList(category)
                                }
                            )
                        }
                    }
                }

                if(productList.isNotEmpty()) {

                    Text(
                        text = "Products",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {

                        items(productList) { product ->
                            ProductItem(
                                modifier = Modifier.padding(8.dp),
                                product = product,
                                onNavigateToProductDetails = {
                                    navigateToProductDetail(product)
                                },
                                onAddToCartClick = {
                                    onAddToCartClick(product)
                                }
                            )
                        }

                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Products Found.",
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBarSection(
    modifier: Modifier = Modifier,
    searchName: String,
    onSearchNameChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClearInput: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.colorPrimary))
            .padding(vertical = 12.dp, horizontal = 15.dp)
    ) {

        OutlinedTextField(
            value = searchName,
            onValueChange = onSearchNameChange,
            placeholder = {
                 Text(text = "Search")
            },
            modifier = Modifier.fillMaxWidth(),
            shape= CircleShape,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search_icon",
                    tint = Color.White
                )
            },
            trailingIcon = {
                if (searchName.isNotBlank()) {
                    IconButton(onClick = onClearInput) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_close),
                            contentDescription = "search_icon",
                            tint = Color.White
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick()
                }
            )
        )
    }
}