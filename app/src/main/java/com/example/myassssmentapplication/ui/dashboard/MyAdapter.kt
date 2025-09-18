package com.example.myassssmentapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myassssmentapplication.R
import com.example.myassssmentapplication.api.Item
import android.util.Log
class MyAdapter(private val onItemClick: (Item) -> Unit) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var dataList = listOf<Item>()

    fun setData(newData: List<Item>) {
        dataList = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val scientificNameText: TextView = itemView.findViewById(R.id.textScientificName)
        private val commonNameText: TextView = itemView.findViewById(R.id.textCommonName)
        private val careLevelText: TextView = itemView.findViewById(R.id.textCareLevel)
        private val lightRequirementText: TextView = itemView.findViewById(R.id.textLightRequirement)
        private val descriptionText: TextView = itemView.findViewById(R.id.textDescription)

        fun bind(item: Item) {
            Log.d("ADAPTER_BIND", "ScientificName: ${item.scientificName}, CommonName: ${item.commonName}")
            scientificNameText.text = item.scientificName
            commonNameText.text = item.commonName
            careLevelText.text = item.careLevel
            lightRequirementText.text = item.lightRequirement
            descriptionText.text = item.description

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}
