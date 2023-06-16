package com.loperilla.rawg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShieldMoon
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loperilla.presentation.HomeScreen
import com.loperilla.presentation.PreviousQuery
import com.loperilla.presentation.game.HomeViewModel
import com.loperilla.rawg.coreui.Routes
import com.loperilla.rawg.coreui.text.TextTitle
import com.loperilla.rawg.coreui.ui.theme.RawgTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = hiltViewModel<MainActivityViewModel>()
            val isDarkThemeSelected = mainViewModel.isDarkThemeSelected.collectAsStateWithLifecycle().value
            RawgTheme(
                darkTheme = isDarkThemeSelected
            ) {
                Surface {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                    TextTitle(
                                        textValue = mainViewModel.topBarTitle.collectAsStateWithLifecycle().value
                                    )
                                },
                                actions = {
                                    IconButton(
                                        onClick = { mainViewModel.selectNewTheme(!isDarkThemeSelected) }
                                    ) {
                                        val icon = if (isDarkThemeSelected) {
                                            Icons.Rounded.WbSunny
                                        } else {
                                            Icons.Rounded.ShieldMoon
                                        }
                                        Icon(imageVector = icon, contentDescription = "ThemeIcon")
                                    }
                                }
                            )
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.HOME.destination,
                            modifier = Modifier
                                .padding(it)
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    bottom = 8.dp
                                )
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            composable(Routes.HOME.destination) {
                                val homeViewModel = hiltViewModel<HomeViewModel>()
                                val previousQueryList = homeViewModel.getPreviousQuery().collectAsStateWithLifecycle(
                                    emptyList(),
                                    lifecycle
                                ).value
                                Column {
                                    SearchBar(
                                        query = homeViewModel.searchInputQuery.collectAsStateWithLifecycle().value,
                                        onQueryChange = homeViewModel::searchInputChange,
                                        onSearch = homeViewModel::searchGamesWithCurrentValue,
                                        active = homeViewModel.searchBarActive.collectAsStateWithLifecycle().value,
                                        onActiveChange = homeViewModel::changeInputActive,
                                        placeholder = {
                                            Text(text = "Search")
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 8.dp)
                                    ) {
                                        // aquí podría poner búsquedar previas.
                                        if (previousQueryList.isEmpty()) {
                                            Text(text = "No hay ná")
                                        } else {
                                            PreviousQuery(
                                                previousQueryList,
                                                homeViewModel::searchGamesWithCurrentValue
                                            )
                                        }
                                    }
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
    }
}
