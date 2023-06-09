package com.loperilla.rawg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loperilla.presentation.genre.GenreViewModel
import com.loperilla.rawg.coreui.Routes
import com.loperilla.rawg.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController: NavHostController = rememberNavController()

                Scaffold {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Routes.HOME) {
                            val genreVM = hiltViewModel<GenreViewModel>()
//                            val creatorViewModel = hiltViewModel<CreatorsViewModel>()
//                            val creatorList = creatorViewModel.getPagingCreators().collectAsLazyPagingItems()

//                            CreatorList(
//                                creatorList = creatorList
//                            )
                        }
                    }
                }
            }
        }
    }
}
