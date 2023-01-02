package com.felix.felix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.felix.R
import com.felix.felix.ui.theme.FelixTheme

@ExperimentalMaterial3Api
@Composable
fun ServiceCard(
    title: String,
    price: String,
    caption: String = "",
    buttonVisible : Boolean = true,
    shape : Shape = CardDefaults.elevatedShape,
    onClick : () -> Unit = {}
) {
    ElevatedCard(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 25.dp),
        onClick = onClick
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
                    modifier = if (buttonVisible) Modifier.height(110.dp) else Modifier.fillMaxHeight()
                ) {
                    ServiceCardTextColumn(title = title, price = price, caption = caption, buttonVisible = buttonVisible)
                }
                if(buttonVisible) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        OutlinedButton(
                            onClick = { onClick() },
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

@Composable
private fun ServiceCardTextColumn(title: String, price: String, caption: String = "", buttonVisible: Boolean = true) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(3.dp))

    if (buttonVisible) {
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
    else {
        Spacer(modifier = Modifier.height(7.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) { //TODO: Must include time icon
            IconBox(Icons.Outlined.Refresh, 40.dp)
            Spacer(modifier = Modifier.width(10.dp))/*TODO: create view model*/
            Text(text = "60 min")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) { //TODO: Must include dollar icon
            IconBox(Icons.Outlined.Info, 40.dp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.price, price),
                fontWeight = FontWeight.SemiBold
            )
        }
    }

}

@ExperimentalMaterial3Api
@Preview("With button")
@Composable
private fun ServiceCardPreview1() {
    FelixTheme {
        ServiceCard(
            title = "Air Conditioner Repair",
            price = "3000",
            caption = "Lorem Ipsum",
            buttonVisible = false
        )
    }
}

@ExperimentalMaterial3Api
@Preview("Without button")
@Composable
private fun ServiceCardPreview2() {
    FelixTheme {
        ServiceCard(
            title = "Air Conditioner Repair",
            price = "3000",
            caption = "Lorem Ipsum",
        )
    }
}