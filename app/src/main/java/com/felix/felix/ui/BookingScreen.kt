package com.felix.felix.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.felix.ui.components.DatePicker
import com.felix.felix.ui.theme.FelixTheme
import com.felix.felix.ui.components.ServiceCard
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.MonthDay
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun BookingScreen(serviceTitle: String, price: String) {
    val shape =
        RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 12.dp, bottomStart = 12.dp)
    var locationText by rememberSaveable { mutableStateOf("User location address") }
    var selectedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var openDateDialog by remember { mutableStateOf(true) }

    Scaffold(topBar = { BookingTopBar() }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            ServiceCard(
                title = serviceTitle,
                price = price,
                buttonVisible = false,
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .fillMaxWidth()
            ) {
                Divider()
                OutlinedTextField(value = locationText,
                    onValueChange = { locationText = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Place,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceColorAtElevation(1.0.dp), shape
                        ),
                    shape = shape,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.0.dp),
                        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                Spacer(Modifier.height(25.dp))

                Text(text = "Select date", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = selectedDate.format(DateTimeFormatter.ofPattern("MMM d, YYYY")),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(7.dp))

                DateRow(
                    onDateClick = {
                    selectedDate = it
                    },
                    onMoreButtonClick = {
                    openDateDialog = true
                    }
                )

                if(openDateDialog) {
                    DatePicker(
                        onDismissRequest = {openDateDialog = false}
                    ) {
                        selectedDate = it
                    }
                }
            }

        }

        Column(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 25.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            ElevatedButton(
                onClick = { /*TODO: Confirm booking*/ },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.size(width = 160.dp, height = 45.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Rounded.Check, contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Confirm", fontSize = 18.sp)
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun BookingTopBar() {
    TopAppBar(title = {
        Text(
            text = "Booking Summary",
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }, navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
            )
        }
    }, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Outlined.Close, contentDescription = "Cancel")
        }
    })
}

@ExperimentalMaterial3Api
@Composable
private fun DateRow(onDateClick: (LocalDate) -> Unit, onMoreButtonClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 0..3) {
            RadioDateButton(LocalDate.now().plusDays(i.toLong()), onDateClick)
        }

        //More Button
        OutlinedButton(
            onClick = { onMoreButtonClick() },
            modifier = Modifier.size(65.dp, 60.dp),
            shape = MaterialTheme.shapes.small,
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "...",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun RadioDateButton(date: LocalDate, onDateClick: (LocalDate) -> Unit) {
    val today = LocalDate.now()
    val tomorrow = today.plusDays(1)

    OutlinedButton(
        onClick = { onDateClick(date) },
        modifier = Modifier.size(65.dp, 60.dp),
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 5.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = when (date) {
                    today -> "Today"
                    tomorrow -> "Tomorrow"
                    else -> capitalize(date.dayOfWeek.name)
                }, fontSize = 11.sp, color = Color.Gray
            )
        }
    }
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
                onClick = { /*TODO*/ }, shape = btnShape, modifier = btnSize
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Cancel", fontSize = 16.sp, textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = { /*TODO*/ }, shape = btnShape, modifier = btnSize
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

private fun capitalize(text: String): String =
    text.substring(0, 1).uppercase() + text.substring(1).lowercase()