package com.kaliostech.RoomHunt.UI

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaliostech.RoomHunt.Data.Receive.DataRepository
import com.kaliostech.RoomHunt.Data.Receive.House
import com.kaliostech.RoomHunt.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var houseAdapter: HouseAdapter
    private var houseList: List<House> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Setup RecyclerView
        setupRecyclerView()
        // Setup Filter Clicks
        setupFilterClicks()
    }

    // Function to set up RecyclerView and fetch house data
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerviewHouse)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dataRepository = DataRepository()
        dataRepository.fetchHouses { houses ->
            lifecycleScope.launch {
                if (houses != null) {
                    houseList = houses
                    houseAdapter = HouseAdapter(this@MainActivity, houseList.toMutableList()) // Pass a mutable list
                    recyclerView.adapter = houseAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load houses. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Function to set up filter clicks
    private fun setupFilterClicks() {
        val allFilter: TextView = findViewById(R.id.all)
        val pgFilter: TextView = findViewById(R.id.only_pg)
        val flatFilter: TextView = findViewById(R.id.flat)

        allFilter.setOnClickListener {
            filterHouses("All")
        }

        pgFilter.setOnClickListener {
            filterHouses("PG")
        }

        flatFilter.setOnClickListener {
            filterHouses("Flat")
        }
    }

    // Function to filter house list based on house type
    private fun filterHouses(filterType: String) {
        val filteredList = when (filterType) {
            "PG" -> houseList.filter { it.houseType.equals("PG", ignoreCase = true) }
            "Flat" -> houseList.filter { it.houseType.equals("Flat", ignoreCase = true) }
            else -> houseList // Show all items when "All" is clicked
        }
        houseAdapter.updateData(filteredList)
    }
}
