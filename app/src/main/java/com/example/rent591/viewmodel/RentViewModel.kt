package com.example.rent591.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rent591.model.RentItem
import com.example.rent591.network.RentApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RentViewModel(private val api: RentApiService) : ViewModel() {

    private val _rentItems = MutableStateFlow<List<RentItem>>(emptyList())
    val rentItems: StateFlow<List<RentItem>> = _rentItems

    private val _streetAvgPrices = MutableStateFlow<List<Pair<String, Double>>>(emptyList())
    val streetAvgPrices: StateFlow<List<Pair<String, Double>>> = _streetAvgPrices

    fun fetchRentData() {
        viewModelScope.launch {
            try {
                val data = api.getRentList()
                _rentItems.value = data
                _streetAvgPrices.value = analyzeRentByStreet(data)
            } catch (e: Exception) {
                // 錯誤處理
            }
        }
    }

    private fun analyzeRentByStreet(rentItems: List<RentItem>): List<Pair<String, Double>> {
        return rentItems
            .mapNotNull { item ->
                val street = extractStreet(item.address)
                street?.let { it to item.price }
            }
            .groupBy({ it.first }, { it.second })
            .mapValues { (_, prices) -> prices.average() }
            .toList()
            .sortedByDescending { it.second }
    }

    private fun extractStreet(address: String): String? {
        val regex = Regex("(.+?[路街道段])")
        return regex.find(address)?.value
    }
}
