package com.felix.felix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.felix.felix.ui.theme.FelixTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FelixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text(text = "Felix",
                                                style = MaterialTheme.typography.headlineLarge) },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                navigationIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = "Menu"
                                        )
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            val selectedItem = remember { mutableStateOf(0) }
                            val items = listOf("Home", "Starred", "Schedule", "Account")
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ) {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = (selectedItem.value == index),
                                        onClick = { selectedItem.value = index },
                                        colors = NavigationBarItemDefaults.colors(
                                            unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                            selectedTextColor = MaterialTheme.colorScheme.onPrimary
                                        ),
                                        label = {Text(item)},
                                        icon = {
                                            when(item){
                                                "Home" -> Icon(
                                                    imageVector = Icons.Outlined.Home,
                                                    contentDescription = "Home"
                                                )
                                                "Starred" -> Icon(
                                                    imageVector = Icons.Outlined.Star,
                                                    contentDescription = "Star"
                                                )
                                                "Schedule" -> Icon(
                                                    imageVector = Icons.Outlined.DateRange,
                                                    contentDescription = "Schedule"
                                                )
                                                "Account" -> Icon(
                                                    imageVector = Icons.Outlined.AccountCircle,
                                                    contentDescription = "Account"
                                                )
                                            }
                                        })
                                }
                            }
                        }
                    ) { paddingValues ->
                        Column(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) { SearchBar()
                            Row(Modifier.fillMaxWidth()) {
                                Text(
                                    text = "Categories",
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                            CategoryCard()
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun CategoryCard() {
    ElevatedCard(
        modifier = Modifier
            .size(width = 150.dp, height = 170.dp)
            .padding(8.dp),
        enabled = true,
        elevation = CardDefaults.elevatedCardElevation(),
        onClick = {/*TODO*/}
    ) { Column(
            modifier = Modifier.fillMaxWidth()
        ) { Image(
                painter = painterResource(id = R.drawable.repair_image),
                contentDescription = "repair"
            )

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically)
            {
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
@Preview(showBackground = true)
@Composable
fun SearchBar() {
    var text = rememberSaveable{ mutableStateOf("") }
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it},
        leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search icon")},
        placeholder = { Text(text = "Search for AC service", color = MaterialTheme.colorScheme.secondary)},
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
    )
}

@Composable
fun DefaultPreview() {
}