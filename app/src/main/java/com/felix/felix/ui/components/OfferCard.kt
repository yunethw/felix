package com.felix.felix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.felix.R
import com.felix.felix.ui.theme.FelixTheme

@ExperimentalMaterial3Api
@Composable
fun OfferCard(title: String, price: Int, caption: String = "") {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 25.dp, vertical = 5.dp),
        onClick = {/*TODO: On offer card click*/}
    ) {
        Row(
            Modifier.fillMaxHeight()
        ) {
            Column(
                Modifier
                    .weight(6f)
                    .padding(start = 10.dp, end = 15.dp, top = 5.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier.height(110.dp)
                ) {
                    OfferCardTextColumn(title = title, price = price, caption = caption)
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(width = 120.dp, height = 40.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),

                    ) {
                        Text(
                            text = "Book Now",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ac_repair),
                contentDescription = null,
                Modifier.weight(5f),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun OfferCardPreview() {
    FelixTheme {
        OfferCard(
            title = "Air Conditioner Repair",
            price = 3000,
            caption = ""
        )
    }
}

@Composable
private fun OfferCardTextColumn(title: String, price: Int, caption: String = "") {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(3.dp))

    Text(
        text = "Starting | $price LKR",
        style = MaterialTheme.typography.bodyLarge
    )

    Spacer(modifier = Modifier.height(2.dp))

    Text(
        text = caption,
        fontSize = 14.sp,
        fontWeight = FontWeight.Light
    )
}
