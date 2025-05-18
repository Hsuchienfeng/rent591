package com.example.rent591.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rent591.viewmodel.RentViewModel

@Composable
fun RentAnalysisScreen(viewModel: RentViewModel) {
    val streetAvgPrices by viewModel.streetAvgPrices.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRentData()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("中和區租金分析", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(streetAvgPrices) { (street, avgPrice) ->
                Text("$street：平均租金 ${avgPrice.toInt()} 元")
                Divider()
            }
        }
    }
}
