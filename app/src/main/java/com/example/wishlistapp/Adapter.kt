package com.example.wishlistapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val items: MutableList<Item>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val name: TextView
        val price: TextView
        val link: TextView
        val parent: ConstraintLayout


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            name = itemView.findViewById(R.id.itemname)
            price = itemView.findViewById(R.id.itemprice)
            link = itemView.findViewById(R.id.itemlink)
            parent = itemView.findViewById(R.id.parent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.name.text = item.name
        holder.price.text = item.price
        holder.link.text = item.link

        holder.link.setOnClickListener{
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                ContextCompat.startActivity(it.context, browserIntent, null)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(it.context, "Invalid URL for " + item.name, Toast.LENGTH_LONG).show()
            }
        }

        holder.parent.setOnLongClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
            false
        }
    }


}