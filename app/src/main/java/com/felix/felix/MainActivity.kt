package com.felix.felix

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.felix.felix.model.ServiceModel
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.felix.felix.model.CategoryModel
import com.felix.felix.model.CustomerModel
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.felix.felix.model.OrderModel
import com.felix.felix.model.ServiceModel
import com.felix.felix.ui.components.CategoryCard
import com.felix.felix.ui.components.OfferCard
import com.felix.felix.ui.theme.FelixTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        OrderModel("Refrigerator",1,"Cleaning",7500F,"2022-03-30","15:00:00")

        var services = ServiceModel()
        var categories = CategoryModel()
        var customer = CustomerModel("mayukhasiriwardena@gmail.com")
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {

            //Load Services
//             Log.i("TEST", services.LoadServices().getData().toString())

            //Get all the Service Title by Category
//            services.LoadServices().getServicesForCategory("4").forEach { service ->
//              Log.i("Title for Cat:", service["title"].toString())
//            }

            //Get all the Services by Category
            //            Log.i("Services for Category:",
//                services.LoadServices().getServicesForCategory("4").toString()
//            )

            //To search for service by title
//            Log.i(
//                "Service by Title:",
//                services.LoadServices().getServiceByTitle("Motorbike")?.entries.toString()
//            )

            //Sub-services and charges listed for service by title
//            if (services.LoadServices().getServiceByTitle("Wall Painting")
//                    ?.get("sub-services") == null
//            ) {
//                Log.i(
//                    "Charges:",
//                    services.LoadServices().getServiceByTitle("Wall Painting")?.get("charges")
//                        .toString()
//                )
//                val charges = services.LoadServices().getServiceByTitle("Wall Painting")
//                    ?.get("charges") as HashMap<String, *>
//                charges.forEach { s, any ->
//                    Log.i("$s", "$any")
//                }
//            } else {
//                Log.i(
//                    "Sub-Services:",
//                    services.LoadServices().getServiceByTitle("Motorbike")?.get("sub-services")
//                        .toString()
//                )
//                val subServices = services.LoadServices().getServiceByTitle("Motorbike")
//                    ?.get("sub-services") as HashMap<String, *>
//                subServices.forEach { s, any ->
//                    Log.i("$s", "$any")
//                }
//            }

            //Load all the category objects
            //            Log.i("Categories: ",categories.LoadCategories().getData().toString())

            Log.i("CUSTOMER DATA", customer.LoadCustomer().getData().toString())

        }
        setContent {

            FelixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { TopBar() },
                        bottomBar = { BottomBar() },
                    ) { paddingValues ->
                        HomeFeed(paddingValues = paddingValues)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Felix",
                style = MaterialTheme.typography.headlineLarge
            )
        },
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
}

@ExperimentalMaterial3Api
@Composable
fun BottomBar() {
    val selectedItem = remember { mutableStateOf(0) }
    val items = listOf("Home", "Favourites", "Calendar", "Account")
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
                label = { Text(item) },
                icon = {
                    when (item) {
                        "Home" -> Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home"
                        )
                        "Favourites" -> Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Star"
                        )
                        "Calendar" -> Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Calendar"
                        )
                        "Account" -> Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = "Account"
                        )
                    }
                }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun HomeFeed(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(25.dp))
        SectionTitle(text = "Categories", onSeeAllClick = {/*TODO: See more*/ })
        Spacer(modifier = Modifier.height(15.dp))
        CategoryRow()
        Spacer(modifier = Modifier.height(30.dp))
        SectionTitle(text = "Special Offers", onSeeAllClick = {/*TODO: See more*/ })
        Spacer(modifier = Modifier.height(15.dp))
        OfferColumn()
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar() {
    var text = rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search icon"
            )
        },
        placeholder = {
            Text(
                text = "Search for AC service",
                color = MaterialTheme.colorScheme.secondary
            )
        },
        modifier = Modifier
            .padding(horizontal = 25.dp)
            .fillMaxWidth()
    )
}

@Composable
fun SectionTitle(text: String, onSeeAllClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
        ClickableText(
            text = AnnotatedString(text = "See All"),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            onClick = onSeeAllClick
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun CategoryRow() {
    val itemsList = (0..5).toList()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 25.dp)
    ) {
        items(itemsList) {
            CategoryCard()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun OfferColumn() {
    for (i in 0..2) {
        OfferCard(
            title = "Air Conditioner Repair",
            price = 3000,
            caption = ""
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun DefaultPreview() {
}

