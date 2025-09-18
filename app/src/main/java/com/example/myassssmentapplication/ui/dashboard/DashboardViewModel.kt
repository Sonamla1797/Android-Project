package com.example.myassssmentapplication.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.myassssmentapplication.api.ApiService
import com.example.myassssmentapplication.ui.dashboard.DashboardResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel(private val apiService: ApiService) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> get() = _uiState

    private var keypass: String = ""

    fun setAccessKey(key: String) {
        keypass = key
        fetchDashboard()
    }

    private fun fetchDashboard() {
        _uiState.value = DashboardUiState(isLoading = true)

        apiService.getDashboard(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(
                call: Call<DashboardResponse>,
                response: Response<DashboardResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    _uiState.value = DashboardUiState(items = response.body()!!.entities)
                } else {
                    _uiState.value = DashboardUiState(error = "Failed to load dashboard")
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                _uiState.value = DashboardUiState(error = "Network error: ${t.message}")
            }
        })
    }
}
