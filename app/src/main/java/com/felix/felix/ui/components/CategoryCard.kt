package com.felix.felix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.felix.felix.R

@ExperimentalMaterial3Api
@Composable
fun CategoryCard(
    categoryName : String,
    categoryImageUrl : String,
    onClick : () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.size(width = 160.dp, height = 190.dp).padding(vertical = 2.dp),
        shape = MaterialTheme.shapes.small,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(categoryImageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.default_placeholder),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth().height(130.dp)
            )


            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = categoryName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
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
//    CategoryCard()
}






























