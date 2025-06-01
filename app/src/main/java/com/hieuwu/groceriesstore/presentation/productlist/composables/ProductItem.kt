package com.hieuwu.groceriesstore.presentation.productlist.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.presentation.core.widgets.PrimarySquareIconButton
import com.hieuwu.groceriesstore.presentation.core.widgets.WebImage

@Composable
fun ProductItem(
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

@Preview
@Composable
private fun ProductPreview() {
    ProductItem(
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