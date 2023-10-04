package com.hieuwu.groceriesstore.presentation.productlist

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.presentation.core.widgets.PrimarySquareIconButton
import com.hieuwu.groceriesstore.presentation.core.widgets.WebImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
fun ProductListScreen(
    navigateUp: () -> Unit,
    showFilter: () -> Unit,
    openProductDetails: (String) -> Unit,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsState()
    ProductListScreen(
        state = viewState,
        navigateUp = navigateUp,
        showFilter = showFilter,
        openProductDetails = openProductDetails,
        addToCart = viewModel::addToCart
    )
}

@Composable
private fun ProductListScreen(
    state: ProductListViewState,
    navigateUp: () -> Unit,
    showFilter: () -> Unit,
    openProductDetails: (String) -> Unit,
    addToCart: (ProductModel) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            ProductListAppBar(
                title = state.categoryTitle,
                navigateUp = navigateUp,
                showFilter = showFilter
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        if (state.productList.isEmpty()) {
            EmptyList(
                modifier = Modifier.padding(paddingValues)
            )
        } else {
            ProductList(
                products = state.productList,
                openProductDetails = openProductDetails,
                addToCart = {
                    addToCart(it)
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Added ${it.name}",
                            duration = SnackbarDuration.Long
                        )
                    }
                },
                contentPadding = paddingValues
            )
        }
    }
}

@Composable
private fun EmptyList(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("list_empty.json")
    )

    LottieAnimation(
        modifier = modifier.size(200.dp),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
private fun ProductList(
    products: ImmutableList<ProductModel>,
    openProductDetails: (String) -> Unit,
    addToCart: (ProductModel) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        contentPadding = contentPadding,
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = products,
            key = { it.id }
        ) {
            Product(
                productModel = it,
                openDetails = { openProductDetails(it.id) },
                addToCart = { addToCart(it) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Product(
    productModel: ProductModel,
    openDetails: () -> Unit,
    addToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = openDetails,
        modifier = modifier.padding(12.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .border(0.7.dp, Color(0xffaaaaaa), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            WebImage(
                model = productModel.image,
                contentDescription = productModel.name,
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = productModel.name.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Text(
                text = productModel.description.orEmpty(),
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${productModel.price} $",
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.colorPrimary),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                PrimarySquareIconButton(onClick = addToCart) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_to_basket),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductListAppBar(
    title: String,
    navigateUp: () -> Unit,
    showFilter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_navigate_back)
                )
            }
        },
        actions = {
            IconButton(
                onClick = showFilter,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_filter_list_36),
                    contentDescription = stringResource(id = R.string.cd_save),
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.colorPrimary),
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}

@Preview
@Composable
private fun ProductListAppBarPreview() {
    ProductListAppBar(title = "Delivery", navigateUp = {}, showFilter = {})
}

@Preview
@Composable
private fun ProductPreview() {
    Product(
        productModel = ProductModel(
            id = "TcF68MpGmIGiq5LOvF7u",
            name = "Chili, Local Grass-Fed Beef",
            price = 5.8,
            image = "https://firebasestorage.googleapis.com/v0/b/groceries-store-ad0eb.appspot.com/o/Vegetables%2Fcsm_peppers-plp-desktop_a9c6971df1.jpg?alt=media&token=f986caa0-d65c-4c76-8186-ff75abd6db1e",
            description = "Chili doesn't get much meatier than this; this is the kind you dream about for game days and crisp winter nights.",
            nutrition = "Chili peppers (Capsicum annuum) are the fruits of Capsicum pepper plants, notable for their hot flavor.  They are members of the nightshade family, related to bell peppers and tomatoes. Many varieties of chili peppers exist, such as cayenne and jalapeño.  Chili peppers are primarily used as a spice and can be cooked or dried and powdered. Powdered, red chili peppers are known as paprika."
        ),
        openDetails = {},
        addToCart = {})
}

@Preview
@Composable
private fun ProductListPreview() {
    Surface(
        modifier = Modifier.height(400.dp)
    ) {
        ProductList(
            products = persistentListOf(
                ProductModel(
                    id = "TcF68MpGmIGiq5LOvF7u",
                    name = "Chili, Local Grass-Fed Beef",
                    price = 5.8,
                    image = "https://firebasestorage.googleapis.com/v0/b/groceries-store-ad0eb.appspot.com/o/Vegetables%2Fcsm_peppers-plp-desktop_a9c6971df1.jpg?alt=media&token=f986caa0-d65c-4c76-8186-ff75abd6db1e",
                    description = "Chili doesn't get much meatier than this; this is the kind you dream about for game days and crisp winter nights.",
                    nutrition = "Chili peppers (Capsicum annuum) are the fruits of Capsicum pepper plants, notable for their hot flavor.  They are members of the nightshade family, related to bell peppers and tomatoes. Many varieties of chili peppers exist, such as cayenne and jalapeño.  Chili peppers are primarily used as a spice and can be cooked or dried and powdered. Powdered, red chili peppers are known as paprika."
                ),
                ProductModel(
                    id = "bhuLZpaY5yqEssty5V7m",
                    name = "Grass-Fed Local Beef Brisket",
                    price = 3.9,
                    image = "https://firebasestorage.googleapis.com/v0/b/groceries-store-ad0eb.appspot.com/o/Vegetables%2Fcsm_spaghetti-squash-overview_b846b6fa65.jpg?alt=media&token=1b68952b-26f0-489a-b979-e23a60b52c45",
                    description = "Braise this lean cut in a seasoned broth alongside vegetables, and you'll be rewarded with the ultimate in comfort food.",
                    nutrition = "Beef is the meat of cattle (Bos taurus).  It is categorized as red meat — a term used for the meat of mammals, which contains higher amounts of iron than chicken or fish.  Usually eaten as roasts, ribs, or steaks, beef is also commonly ground or minced. Patties of ground beef are often used in hamburgers.  Processed beef products include corned beef, beef jerky, and sausages."
                )
            ),
            openProductDetails = {},
            addToCart = {},
            contentPadding = PaddingValues(0.dp)
        )
    }
}
@Preview
@Composable
private fun ProductListScreenPreview() {
    ProductListScreen(
        state = ProductListViewState(
            "Meat & Fish",
            persistentListOf()
        ),
        navigateUp = {},
        showFilter = {},
        openProductDetails = {},
        addToCart = {}
    )
}
