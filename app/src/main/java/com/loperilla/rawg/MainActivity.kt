package com.loperilla.rawg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loperilla.presentation.HomeScreen
import com.loperilla.presentation.game.HomeViewModel
import com.loperilla.rawg.coreui.Routes
import com.loperilla.rawg.coreui.ui.theme.RawgTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RawgTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME,
                        modifier = Modifier
                            .padding(it)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        composable(Routes.HOME) {
                            val homeViewModel = hiltViewModel<HomeViewModel>()
//                            val creatorViewModel = hiltViewModel<CreatorsViewModel>()
//                            val creatorList = creatorViewModel.getPagingCreators().collectAsLazyPagingItems()

//                            CreatorList(
//                                creatorList = creatorList
//                            )

                            HomeScreen(
                                homeViewModel.getAllGames().collectAsLazyPagingItems(),
                                Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}
