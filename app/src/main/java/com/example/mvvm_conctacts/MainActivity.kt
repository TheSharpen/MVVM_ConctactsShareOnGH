package com.example.mvvm_conctacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvm_conctacts.ui.add_edit_contact.AddEditContactScreen
import com.example.mvvm_conctacts.ui.contact_list.ContactListScreen
import com.example.mvvm_conctacts.ui.theme.MVVM_ConctactsTheme
import com.example.mvvm_conctacts.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_ConctactsTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.CONTACT_LIST) {
                    composable(Routes.CONTACT_LIST) {

                        ContactListScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }

                    composable(route = Routes.ADD_EDIT_CONTACT + "?personId={personId}",
                    arguments = listOf(
                        navArgument(
                            name = "personId"
                        ) {
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    ))
                    {

                        AddEditContactScreen(onPopBackStack = {
                            navController.popBackStack()
                        })
                    }
                }

            }
        }
    }
}

