package com.kaliostech.RoomHunt.UI
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.kaliostech.RoomHunt.R

class EnquiryFormActivity : AppCompatActivity() {

    private lateinit var btn_submit_enquiry: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(
            Color.WHITE,Color.WHITE
        ) )
        setContentView(R.layout.activity_enquiry_form)

        btn_submit_enquiry = findViewById(R.id.btn_submit_enquiry)

        // Retrieve the address from the Intent
        val address = intent.getStringExtra("intrested_house_address")

        // Find the TextInputLayout first, then get the TextInputEditText
        val addressEditText = findViewById<TextInputEditText>(R.id.edit_room_address)
        addressEditText.setText(address)

        // Set the address in the TextInputEditText
        addressEditText.setText(address)

        btn_submit_enquiry.setOnClickListener {
            Toast.makeText(this, "Enquiry Submitted", Toast.LENGTH_SHORT).show()
        }
    }
}
