package com.example.universityapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.universityapp.domain.model.City
import com.example.universityapp.presentation.common.CityExpandableCard.CityExpandableCard
import com.example.universityapp.presentation.common.CollapseAllButton
import com.example.universityapp.presentation.common.ErrorScreen
import com.example.universityapp.util.Constants.SmallPadding
import retrofit2.HttpException

@Composable
fun HomeScreen(
    cities: LazyPagingItems<City>
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val handlePagingResult = handlePagingResult(cities = cities)
            if (handlePagingResult) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(SmallPadding),
                    contentPadding = PaddingValues(all = SmallPadding)
                ) {
                    items(count = cities.itemCount) {
                        cities[it]?.let { city ->
                            CityExpandableCard(city = city)
                        }
                    }
                }
            }

        }
        CollapseAllButton()
    }
}

@Composable
fun handlePagingResult(cities: LazyPagingItems<City>): Boolean {
    val loadState = cities.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            false
        }

        error != null -> {
            ErrorScreen()
            false
        }

        cities.itemCount == 0 -> {
            ErrorScreen()
            false
        }

        else -> true
    }
}