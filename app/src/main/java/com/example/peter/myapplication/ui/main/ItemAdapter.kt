package com.example.peter.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.peter.myapplication.R
import com.example.peter.myapplication.data.Item
import java.util.ArrayList
import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {

    private var items: List<Item> = ArrayList()
    private lateinit var  mContext :Context
    private val typesAr= arrayOf("Bag","Sachet","Cup","Bottle","Piece", "Carton", "Mudu")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        mContext=parent.context

        return ItemHolder(itemView)

    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val (_, title, price, priority, quanType, quantity) = items[position]

        holder.titletv.text = title


        var prir = ""
        var color : Int =R.color.colorPrimary

        when(priority){
            1-> {prir="LOW"; color=R.color.colorLow}
            2-> {prir="MID"; color=R.color.colorMedium}
            3-> {prir="HIGH"; color=R.color.colorHigh}
        }
        var qtype=typesAr[quanType]
        if (quantity >1) { qtype=qtype +"s"}

        val pric= "$price Naira"
        holder.pricetv.text = pric
        holder.priortv.text=prir

        val qString= quantity.toString()+ " " + qtype
        holder.quantitytv.text=qString

        val priorityCircle = holder.priortv.background as GradientDrawable
        // Get the appropriate background color based on the priority
       val colour = ContextCompat.getColor( mContext ,color)
        priorityCircle.setColor(colour)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun getItemAt(position: Int):Item{
        return items[position]
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

   inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val titletv = itemView.findViewById(R.id.title) as TextView
            val pricetv = itemView.findViewById(R.id.price) as TextView
            val priortv = itemView.findViewById(R.id.prior) as TextView
            val quantitytv = itemView.findViewById(R.id.quantity) as TextView

        //all this was wrapped in an init block with declarations outside the block



    }



}