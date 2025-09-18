package com.example.myassssmentapplication.ui.dashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.api.Item

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Get the Item from intent
        val item = intent.getSerializableExtra("EXTRA_ITEM") as? Item

        // Reference UI elements
        val scientificNameText: TextView = findViewById(R.id.textScientificName)
        val commonNameText: TextView = findViewById(R.id.textCommonName)
        val careLevelText: TextView = findViewById(R.id.textCareLevel)
        val lightRequirementText: TextView = findViewById(R.id.textLightRequirement)
        val descriptionText: TextView = findViewById(R.id.textDescription)

        // Set values
        item?.let {
            scientificNameText.text = it.scientificName
            commonNameText.text = it.commonName
            careLevelText.text = it.careLevel
            lightRequirementText.text = it.lightRequirement
            descriptionText.text = it.description
        }
    }
}
