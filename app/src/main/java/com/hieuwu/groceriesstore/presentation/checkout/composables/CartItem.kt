package com.hieuwu.groceriesstore.presentation.checkout.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.domain.models.LineItemModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    lineItem: LineItemModel,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Image
            GlideImage(
                model = lineItem.image,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Name
                Text(
                    text = lineItem.name!!,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                // Price
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = "${lineItem.price} $",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                // Quantity Row
                Row(
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_remove_36),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${lineItem.quantity}",
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(5.dp))
                            .border(
                                width = 0.5.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_add_36),
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 16.dp),
                text = "${lineItem.subtotal} $",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_delete),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 4.dp, end = 4.dp)
                    .size(36.dp)
                    .clickable { onDeleteClick() }
            )
        }
    }
}