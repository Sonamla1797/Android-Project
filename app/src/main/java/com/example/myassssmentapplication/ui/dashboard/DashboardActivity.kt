package com.example.myassssmentapplication.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.RetrofitClient
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import android.util.Log

class DashboardActivity : AppCompatActivity() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val keypass = intent.getStringExtra("EXTRA_KEYPASS") ?: ""

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MyAdapter { item ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_ITEM", item)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        progressBar = findViewById(R.id.progressBar)
        errorText = findViewById(R.id.errorText)

        // Updated line: no .create() needed
        val apiService = RetrofitClient.instance
        viewModel = ViewModelProvider(
            this,
            DashboardViewModelFactory(apiService)
        )[DashboardViewModel::class.java]

        viewModel.setAccessKey(keypass)

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when {
                    state.isLoading -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        errorText.visibility = View.GONE
                    }
                    state.error != null -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = state.error
                    }
                    else -> {
                        progressBar.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        errorText.visibility = View.GONE
                        Log.d("DASHBOARD_ACTIVITY", "Items received: ${state.items.size}")
                        state.items.forEach { Log.d("DASHBOARD_ACTIVITY", it.scientificName) }
                        adapter.setData(state.items)
                    }
                }
            }
        }
    }
}
