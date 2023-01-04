package com.felix.felix.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.felix.ui.components.DatePicker
import com.felix.felix.ui.components.ServiceCard
import com.felix.felix.ui.theme.FelixTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun BookingScreen(
    serviceTitle: String,
    price: String,
    onBackButtonClick: () -> Unit,
    onCancelButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    val shape =
        RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomEnd = 12.dp, bottomStart = 12.dp)
    var locationText by rememberSaveable { mutableStateOf("User location address") }
    var selectedDate by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var selectedTime by rememberSaveable { mutableStateOf("8.00 am - 9.00 am") }
    var openDateDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { BookingTopBar(onBackButtonClick, onCancelButtonClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
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

                Spacer(Modifier.height(25.dp))

                Text(text = "Select time slot", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = selectedTime,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(7.dp))

                TimeColumn()

                Spacer(modifier = Modifier.height(7.dp))
                Divider()
                Spacer(modifier = Modifier.height(7.dp))
                Text(text = "Payment summary", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("Service")
                        Text("Discount")
                        Text("Travel")
                        Text("Tax")
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Total", fontWeight = FontWeight.SemiBold)
                    }
                    Column(Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                        Text("$price.00")
                        Text("0.00")
                        Text("300.00")
                        Text("280.00")
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("3580.00", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    ElevatedButton(
                        onClick =  onConfirmButtonClick,
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier
                            .size(width = 190.dp, height = 60.dp)
                            .padding(5.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(13.dp))
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
    }
}

@ExperimentalMaterial3Api
@Composable
private fun BookingTopBar(onBackButtonClick: () -> Unit, onCancelButtonClick: () -> Unit) {
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
        IconButton(onClick = onBackButtonClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
            )
        }
    }, actions = {
        IconButton(onClick = onCancelButtonClick) {
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
private fun TimeColumn(startTime: Int = 8) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (r in 0..2) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for( i in 0..1) {
                    TimeButton(time = (startTime + r + i))
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TimeButton(time : Int) {
    OutlinedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(165.dp, 60.dp),
        shape = MaterialTheme.shapes.small,
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        Text(text = "$time.00 - ${time+1}.00", fontSize = 20.sp, color = MaterialTheme.colorScheme.onSurface)
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
@Preview
@Composable
private fun BookingPreview() {
    FelixTheme {
        BookingScreen("Air Conditioner Repair", "3000", {}, {}, {})
    }
}

private fun capitalize(text: String): String =
    text.substring(0, 1).uppercase() + text.substring(1).lowercase()