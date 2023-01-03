package com.felix.felix

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
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
import coil.compose.AsyncImage
import com.felix.felix.model.CategoryModel
import com.felix.felix.model.CustomerModel
import com.felix.felix.model.OrderModel
import com.felix.felix.model.ServiceModel
import com.felix.felix.ui.theme.FelixTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //OrderModel("Refrigerator",1,"Cleaning",7500F,"2022-03-30","15:00:00")

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

            services.LoadServices().getSubServicesFrontPage()
//            Log.i("Sub-Services:",services.LoadServices().getSubServicesFrontPage().count().toString())
//            Log.i("CUSTOMER DATA", customer.LoadCustomer().getData().toString())

        }
        setContent {
            FelixTheme {
                FelixApp()
            }
        }
    }
}

