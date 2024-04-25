package com.example.universityapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.universityapp.domain.model.City
import com.example.universityapp.presentation.common.CityExpandableCard
import com.example.universityapp.util.Constants.SmallPadding
import retrofit2.HttpException

@Composable
fun HomeScreen(
    modifier: Modifier,
    cities: LazyPagingItems<City>,
) {
    Column(
        modifier = modifier
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
                        CityExpandableCard(city = city, expanded = false)
                    }
                }
            }
        }
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
            // ShimmerEffect()
            false
        }

        error != null -> {
            if (error.error is HttpException) {
                //EmptyScreen(message = stringResource(R.string.search_no_query)) // Search Query is ""
            } else {

                // EmptyScreen()
            }


            false
        }

        cities.itemCount == 0 -> {
            //EmptyScreen(message = stringResource(R.string.empty_search_list)) // Search event returns empty
            false
        }

        else -> true
    }
}