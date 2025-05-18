package com.example.rent591.network

import com.example.rent591.model.RentItem
import retrofit2.http.GET

interface RentApiService {
    @GET("your_api_endpoint") // 請替換成你自己的 API 路徑
    suspend fun getRentList(): List<RentItem>
}
