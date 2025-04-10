package com.app.motocare

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.app.AlertDialog
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton


class LandingActivity : Activity() {

    data class Motorcycle(var makeModel: String, val mileage: String, val year: String, val vin: String)

    private var loggedInUserEmail: String = ""
    
    
    private lateinit var motorcycles: MutableList<Motorcycle>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_landing)        
        UserDataManager.loadData(applicationContext)

        // Retrieve the logged-in user's email from the intent
        loggedInUserEmail = intent.getStringExtra("user_email") ?: ""
        if (loggedInUserEmail.isEmpty()) {
            Toast.makeText(this, "Error: No user email provided", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        
        //Navigation button setup
        val nav_stats = findViewById<Button>(R.id.nav_stats)
        val nav_service = findViewById<Button>(R.id.nav_service)        
        val nav_profile = findViewById<Button>(R.id.nav_profile)
        val nav_settings = findViewById<Button>(R.id.nav_settings)

        nav_stats.setOnClickListener {
            startActivity(Intent(this, StatsActivity::class.java))
        }
        
        val textViewNoMotorcycles = findViewById<TextView>(R.id.textView_no_motorcycles)
        nav_stats.setOnClickListener {
            val intent = Intent(this, StatsActivity::class.java)            
        }
        nav_service.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }


        nav_profile.setOnClickListener {            
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }        nav_settings.setOnClickListener {
            startActivity(intent)
        }

        nav_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }        

        

        // RecyclerView setup
        val recyclerViewMotorcycles = findViewById<RecyclerView>(R.id.recyclerView_motorcycles)        
        
        motorcycles = getUserMotorcycles()


        // RecyclerView Adapter
        class MotorcycleAdapter(private val motorcycles: MutableList<Motorcycle>) : RecyclerView.Adapter<MotorcycleAdapter.ViewHolder>() {
            class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val imageViewMotorcycle: ImageView = view.findViewById(R.id.imageView_motorcycle)                val textViewMakeModel: TextView = view.findViewById(R.id.textView_makeModel)
                val textViewMileage: TextView = view.findViewById(R.id.textView_mileage)                
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_motorcycle, parent, false)
                return ViewHolder(view)
            }
            @SuppressLint("SetTextI18n")

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val motorcycle = motorcycles[position]
                holder.textViewMakeModel.text = motorcycle.makeModel                
                holder.textViewMileage.text = motorcycle.mileage                
                holder.imageViewMotorcycle.setImageResource(android.R.drawable.ic_menu_directions)            
            }        

            override fun getItemCount(): Int {
                return motorcycles.size
            }
        }

        // Create and set adapter
        val adapter = MotorcycleAdapter(motorcycles)

        if (motorcycles.isEmpty()) {
            textViewNoMotorcycles.visibility = View.VISIBLE
        } else {
            textViewNoMotorcycles.visibility = View.GONE
        }

        recyclerViewMotorcycles.adapter = adapter

        // Set layout manager
        recyclerViewMotorcycles.layoutManager = LinearLayoutManager(this)

        //Floating action button setup
        val fabAddMotorcycle = findViewById<FloatingActionButton>(R.id.fab_add_motorcycle)
        fabAddMotorcycle.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_motorcycle, null)            
            val edittextYear = dialogView.findViewById<EditText>(R.id.edittext_year)
            val edittextVin = dialogView.findViewById<EditText>(R.id.edittext_vin)
            
            
            
            val edittextMake = dialogView.findViewById<EditText>(R.id.edittext_make)
            val edittextModel = dialogView.findViewById<EditText>(R.id.edittext_model)            
            val buttonSave = dialogView.findViewById<Button>(R.id.button_save)

            val dialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Save", null)
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            dialog.setOnShowListener {
                val button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                button.setOnClickListener {
                    val year = edittextYear.text.toString().trim()
                    val vin = edittextVin.text.toString().trim()
                    val make = edittextMake.text.toString().trim()
                    val model = edittextModel.text.toString().trim()

                    if (TextUtils.isEmpty(make) || TextUtils.isEmpty(model)) {
                        Toast.makeText(this, "Please enter both Make and Model", Toast.LENGTH_SHORT).show()
                    } else {
                        val userMotorcycles = UserDataManager.getUser(loggedInUserEmail)?.second                        
                        
                        val newMotorcycle = Motorcycle("$make $model ($year) - VIN: $vin", "0 kms", year, vin)
                        
                        UserDataManager.addMotorcycle(loggedInUserEmail, newMotorcycle)
                        UserDataManager.saveData(applicationContext)
                        adapter.notifyDataSetChanged()
                        dialog.dismiss()
                    }
                }
            }

            
            dialog.show()
        }
    }    
    
    private fun getUserMotorcycles(): MutableList<Motorcycle> {
        val user = UserDataManager.getUser(loggedInUserEmail)
        return user?.second ?: mutableListOf<Motorcycle>()
    }
}
