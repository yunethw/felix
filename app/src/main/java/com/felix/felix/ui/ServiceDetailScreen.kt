package com.felix.felix.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.felix.felix.R
import com.felix.felix.model.BookingUiState
import com.felix.felix.model.HomeUiState
import com.felix.felix.ui.components.IconBox
import com.felix.felix.ui.theme.FelixTheme
import com.felix.felix.viewmodel.BookingViewModel
import com.felix.felix.viewmodel.HomeViewModel

@ExperimentalMaterial3Api
@Composable
fun ServiceDetailsScreen(
    selectedSubServiceState: BookingUiState.SelectedSubService,
    onBackButtonClick : () -> Unit,
    onBookButtonClick : () -> Unit
) {
    Scaffold(
        topBar = { ServiceTopBar(selectedSubServiceState.title, onBackButtonClick) },
        bottomBar = { ServiceBottomBar(selectedSubServiceState.price, onBookButtonClick) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(selectedSubServiceState.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ac_repair),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .padding(25.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = selectedSubServiceState.title, style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconBox(Icons.Rounded.Star, 45.dp)
                    Spacer(modifier = Modifier.width(10.dp))/*TODO: create view model*/
                    Text(text = "4.8")
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "(125 Reviews)", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) { //TODO: Must include time icon
                    IconBox(Icons.Outlined.Refresh, 45.dp)
                    Spacer(modifier = Modifier.width(10.dp))/*TODO: create view model*/
                    Text(text = "60 min")
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) { //TODO: Must include dollar icon
                    IconBox(Icons.Outlined.Info, 45.dp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(R.string.price, selectedSubServiceState.price),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "About Service",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = selectedSubServiceState.description)
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun ServiceTopBar(title: String, onBackButtonClick: () -> Unit) {
    TopAppBar(title = {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }, navigationIcon = {
        IconButton(onClick = onBackButtonClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
            )
        }
    }, actions = {
        var checked by remember { mutableStateOf(false) }
        
        IconToggleButton(checked = checked, onCheckedChange = {checked = it}) {
            if (checked) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favourite")
            } else {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Not Favourite")
            }
        }
    })
}

@ExperimentalMaterial3Api
@Composable
private fun ServiceBottomBar(servicePrice: String, onBookButtonClick: () -> Unit) {
    val btnShape = MaterialTheme.shapes.small

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.price, servicePrice),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Button(
                onClick = onBookButtonClick,
                shape = btnShape,
                modifier = Modifier.size(width = 150.dp, height = 45.dp)
            ) {
                Text(
                    text = "Book Now", fontSize = 16.sp, textAlign = TextAlign.Center
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview
private fun ServiceDetailsScreenPreview() {
    FelixTheme {
//        ServiceDetailsScreen(
//            "Air Conditioner Repair",
//            "3000",
//            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia, molestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum",
//            onBackButtonClick = {},
//            onBookButtonClick = {}
//        )
    }
}



