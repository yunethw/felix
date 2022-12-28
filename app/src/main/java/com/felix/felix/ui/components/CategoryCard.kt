package com.felix.felix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.felix.R

@ExperimentalMaterial3Api
@Composable
fun CategoryCard() { //TODO: Card should take parameters for dynamic display
    ElevatedCard(
        modifier = Modifier.size(width = 160.dp, height = 190.dp).padding(vertical = 2.dp),
        shape = MaterialTheme.shapes.small,
        onClick = {/*TODO*/ }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.repair_image),
                contentDescription = "repair",
                Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                contentScale = ContentScale.FillBounds
            )


            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Appliance Repair and Service",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(all = 5.dp)
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview("default")
@Composable
private fun CategoryCardPreview()
{
    CategoryCard()
}






























