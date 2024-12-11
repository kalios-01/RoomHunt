package com.kaliostech.RoomHunt.UI

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kaliostech.RoomHunt.R
import com.kaliostech.RoomHunt.Data.Receive.House

class HouseAdapter(
    private val context: Context,
    private var houseList: MutableList<House> // Changed to mutable list
) : RecyclerView.Adapter<HouseAdapter.HouseViewHolder>() {

    class HouseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val houseTypeTextView: TextView = itemView.findViewById(R.id.txtview_house_type)
        val houseImageView: ImageView = itemView.findViewById(R.id.background)
        val houseStatusTextView: TextView = itemView.findViewById(R.id.house_status)
        val btnDetails: ImageView = itemView.findViewById(R.id.btn_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_house, parent, false)
        return HouseViewHolder(view)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val currentItem = houseList[position]

        holder.houseTypeTextView.text = currentItem.houseType
        holder.houseStatusTextView.text = currentItem.houseStatus

        // Load house image using Coil
        holder.houseImageView.load(currentItem.imageUrl) {
            crossfade(true)
        }

        holder.btnDetails.setOnClickListener {
            val intent = Intent(context, HouseDetailsActivity::class.java).apply {
                putExtra("houseType", currentItem.houseType)
                putExtra("imageUrl", currentItem.imageUrl)
                putExtra("sliderImageUrls", ArrayList(currentItem.sliderImageUrls))
                putExtra("houseStatus", currentItem.houseStatus)
                putExtra("rent", currentItem.rent)
                putExtra("deposit", currentItem.deposit)
                putExtra("address", currentItem.address)
                putExtra("bhk_number", currentItem.bhk_number)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = houseList.size

    // Method to update the data in the adapter and notify changes
    fun updateData(newList: List<House>) {
        houseList.clear() // Clear the current list
        houseList.addAll(newList) // Add the new list
        notifyDataSetChanged() // Notify the adapter that data has changed
    }
}
