package com.kaliostech.RoomHunt.UI
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kaliostech.RoomHunt.R

class HouseDetailsActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var dotLayout: LinearLayout
    private lateinit var txtview_numberof_bhk: TextView
    private lateinit var txtview_rent: TextView
    private lateinit var text_deposit: TextView
    private lateinit var txtview_address: TextView
    private lateinit var btn_enquiry: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_house_details)

        // Initialize views
        viewPager = findViewById(R.id.slider_viewPager)
        dotLayout = findViewById(R.id.dotLayout)
        txtview_numberof_bhk = findViewById(R.id.txtview_numberof_bhk)
        txtview_rent = findViewById(R.id.txtview_rent)
        text_deposit = findViewById(R.id.text_deposit)
        txtview_address = findViewById(R.id.txtview_address)
        btn_enquiry = findViewById(R.id.btn_enquiry)

        // Retrieve the data from the intent
        intent?.let {
            val imageUrls = it.getStringArrayListExtra("sliderImageUrls") ?: emptyList()
            val bhk_number = it.getStringExtra("bhk_number")
            val rent = it.getStringExtra("rent")
            val deposit = it.getStringExtra("deposit")
            val address = it.getStringExtra("address")

            // Populate the views with the received data
            txtview_numberof_bhk.text = "BHKs : $bhk_number"
            txtview_rent.text = "Rent : $rent"
            txtview_address.text = "Address : $address"
            text_deposit.text = "Deposit : $deposit"

            // Set up ViewPager with the ImageSliderAdapter
            val imageSliderAdapter = ImageSliderAdapter(this, imageUrls)
            viewPager.adapter = imageSliderAdapter

            // Create and add dot indicators
            addDotIndicators(imageUrls.size)
            setupDotIndicators()


            // Set up a page change listener to update dot indicators
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    updateDotIndicators(position)
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
        }

        btn_enquiry.setOnClickListener {
            // Create the Intent
            val intent = Intent(this, EnquiryFormActivity::class.java).apply {
                // Add the address as an extra to the Intent
                putExtra("intrested_house_address", txtview_address.text.toString())
            }
            // Start the EnquiryFormActivity with the Intent
            startActivity(intent)
        }
    }

    private fun setupDotIndicators() {
        updateDotIndicators(0) // Set the first dot as active
    }

    private fun addDotIndicators(count: Int) {
        dotLayout.removeAllViews()
        for (i in 0 until count) {
            val dotView = layoutInflater.inflate(R.layout.dot_indicator, dotLayout, false)
            dotLayout.addView(dotView)
        }
    }

    private fun updateDotIndicators(position: Int) {
        for (i in 0 until dotLayout.childCount) {
            val dotView = dotLayout.getChildAt(i) as ImageView
            dotView.setImageResource(if (i == position) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }
}
