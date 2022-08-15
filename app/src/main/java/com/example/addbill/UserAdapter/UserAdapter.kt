package com.example.addbill.UserAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.addbill.R
import com.example.addbill.model.User_list

class UserAdapter(
    private val context: Context,
    private var list: List<User_list>,
    private val listener: UserAdapterfuntion
) : RecyclerView.Adapter<UserAdapter.userviewholder>() {


    fun updateList(list: List<User_list>) {
        this.list = list
        notifyDataSetChanged()


    }

    class userviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val userimg = itemview.findViewById<ImageView>(R.id.circleImageView)
        val username = itemview.findViewById<TextView>(R.id.itemname)
        val userphone = itemview.findViewById<TextView>(R.id.amount)
        val delete = itemview.findViewById<ImageView>(R.id.deleting)
        val addBill = itemview.findViewById<ImageView>(R.id.bill)
        val update = itemview.findViewById<ImageView>(R.id.updating)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userviewholder {


        val viewholder =
            userviewholder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false))


        viewholder.update.setOnClickListener {

            listener.onItemUpadte(list[viewholder.absoluteAdapterPosition],it)
        }
        viewholder.delete.setOnClickListener {



            listener.onDeleteUser(list[viewholder.absoluteAdapterPosition])



        }







        return viewholder

    }

    override fun onBindViewHolder(holder: userviewholder, position: Int) {


        val data = list[position]
        Glide.with(context).load(data.userImg?.toUri()).into(holder.userimg)
        holder.username.text = data.username
        holder.userphone.text = data.phoneNumber.toString()


        holder.addBill.setOnClickListener {

            listener.onbilluser(data,it)

        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


}

interface UserAdapterfuntion {


    fun onDeleteUser(userList: User_list)
    fun onItemUpadte(userList: User_list, view: View)
    fun onbilluser(userList: User_list, view: View)



}
