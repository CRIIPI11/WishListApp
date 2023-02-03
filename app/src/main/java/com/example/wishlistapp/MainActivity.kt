package com.example.wishlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var items: MutableList<Item> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.list)
        val submit = findViewById<Button>(R.id.submit)
        val name = findViewById<EditText>(R.id.name)
        val price = findViewById<EditText>(R.id.price)
        val link = findViewById<EditText>(R.id.shoplink)
        val adapter = Adapter(items)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        submit.setOnClickListener {

            val item = Item(name.text.toString(),price.text.toString(),link.text.toString())
            items.add(item)
            adapter.notifyDataSetChanged()
        }
    }
}