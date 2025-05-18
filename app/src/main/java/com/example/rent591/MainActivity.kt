package com.example.rent591

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rent591.network.RentApiService
import com.example.rent591.ui.RentAnalysisScreen
import com.example.rent591.viewmodel.RentViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = Retrofit.Builder()
            .baseUrl("https://your.api.base.url/") // 請替換為實際的 API 基礎網址
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RentApiService::class.java)
        val viewModel = RentViewModel(api)

        setContent {
            RentAnalysisScreen(viewModel)
        }
    }
}
