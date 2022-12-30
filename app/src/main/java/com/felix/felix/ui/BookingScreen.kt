package com.felix.felix.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.felix.R
import com.felix.felix.ui.theme.FelixTheme
import java.util.*
import com.felix.felix.ui.components.DatePicker
import com.felix.felix.ui.components.OfferCard

@ExperimentalMaterial3Api
@Composable
fun BookingScreen(serviceTitle : String, price : String) {
    Scaffold(
        topBar = { BookingTopBar() },
        bottomBar = { BookingBottomBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            Spacer(modifier = Modifier.height(20.dp))
            OfferCard(title = serviceTitle, price = price, buttonVisible = false)

        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun BookingTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Booking Details",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                )
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun BookingBottomBar() {
    val btnShape = MaterialTheme.shapes.small
    val btnSize = Modifier.size(width = 150.dp, height = 45.dp)

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = btnShape,
                modifier = btnSize
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = { /*TODO*/ },
                shape = btnShape,
                modifier = btnSize
            ) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Confirm", fontSize = 16.sp, textAlign = TextAlign.Center
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun BookingPreview() {
    FelixTheme {
        BookingScreen("Air Conditioner Repair", "3000")
    }
}