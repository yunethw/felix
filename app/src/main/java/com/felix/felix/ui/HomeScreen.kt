package com.felix.felix.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.felix.ui.components.CategoryCard
import com.felix.felix.ui.components.ServiceCard
import com.felix.felix.ui.theme.FelixTheme
import org.json.JSONObject

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    onServiceCardClick: (HashMap<String, String>?) -> Unit = {},
    subServiceList: List<HashMap<String, String>>?,
    onCategoryCardClick : () -> Unit = {},
    categoryList : List<Pair<String, String>>

) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomBar() },
        ) { paddingValues ->
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
                CategoryRow(categoryList)
                Spacer(modifier = Modifier.height(30.dp))
                SectionTitle(text = "Popular Services", onSeeAllClick = {/*TODO: See more*/ })
                Spacer(modifier = Modifier.height(15.dp))
                OfferColumn(
                    subServiceList = subServiceList,
                    onServiceCardClick = onServiceCardClick
                )
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
fun CategoryRow(
    categoryList: List<Pair<String, String>> = listOf(
        Pair("",""), Pair("",""), Pair("",""), Pair("",""), Pair("",""), Pair("","")
    )
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 25.dp)
    ) {
        items(if(categoryList.size < 6) categoryList else categoryList.subList(0, 6)) {pair ->
            CategoryCard(
                categoryName = pair.first,
                categoryImageUrl = pair.second,
                onClick = {}
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun OfferColumn(
    subServiceList: List<HashMap<String, String>>?,
    onServiceCardClick: (HashMap<String, String>?) -> Unit
) {
    val subList = subServiceList?.subList(0, 3)

    if (subList != null) {
        for (item in subList) {
            ServiceCard(
                subService = item,
                title = item["title"] ?: "",
                price = item["price"] ?: "0.00",
                caption = item["description"] ?: "",
                imageUrl = item["imageUrl"] ?: ""
            ) {
                onServiceCardClick(it)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun DefaultPreview() {
    FelixTheme {
//        HomeScreen()
    }
}