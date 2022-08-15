package com.example.addbill.UserAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.addbill.R
import com.example.addbill.model.Info
import de.hdodenhof.circleimageview.CircleImageView

class ByUserBill(private val context: Context, private val list: List<Info>,private val listener:deleteUserbill) :
    RecyclerView.Adapter<ByUserBill.billviewholder>() {


    inner class billviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val itemname: TextView = itemview.findViewById(R.id.itemname)
        val itemprice: TextView = itemview.findViewById(R.id.amount)
        val delete: ImageView = itemview.findViewById(R.id.delete)
        val itemImg = itemView.findViewById<CircleImageView>(R.id.circleImageView)

        val update = itemview.findViewById<ImageView>(R.id.updatingBill)



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): billviewholder {

        val viewholder = billviewholder(
            LayoutInflater.from(context).inflate(R.layout.billbyuser, parent, false)
        )


        viewholder.update.setOnClickListener {


            listener.updateBill(list[viewholder.absoluteAdapterPosition],it)




        }
        viewholder.delete.setOnClickListener {


            listener.deleteBill(list[viewholder.absoluteAdapterPosition],it)





        }

        return viewholder

    }

    override fun onBindViewHolder(holder: billviewholder, position: Int) {

        holder.itemname.text = list[position].Itemname
        holder.itemprice.text = list[position].Amount.toString() + "₹"
        Glide.with(context).load(list[position].ItemImg.toUri()).into(holder.itemImg)




    }

    override fun getItemCount(): Int {

        return list.size
    }
}

interface deleteUserbill
{
    fun deleteBill(info: Info, view: View)
    fun updateBill(info: Info, view: View)
}