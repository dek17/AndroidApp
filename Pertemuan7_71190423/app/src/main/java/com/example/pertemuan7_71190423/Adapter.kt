package com.example.pertemuan7_71190423

import android.media.Image
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Adapter (private val listContact: ArrayList<Contacts>):
    RecyclerView.Adapter<Adapter.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.main_layout, parent, false)
        return ContactViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: Adapter.ContactViewHolder, position: Int) {
        val currentItem =  listContact[position]
        holder.imageTitle.setImageResource(currentItem.Photos)
        holder.titleName.text = currentItem.Names
        holder.phoneNumber.text = currentItem.Phones
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    private lateinit var mListener : onItemClickList

    interface onItemClickList{
        fun OnItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickList){
        mListener = listener
    }


    class ContactViewHolder(itemView: View, listener: onItemClickList): RecyclerView.ViewHolder(itemView){
        val imageTitle : ImageView = itemView.findViewById(R.id.photo)
        val titleName : TextView = itemView.findViewById(R.id.Name)
        val phoneNumber : TextView = itemView.findViewById(R.id.Phone)

        init{
            itemView.setOnClickListener {
                listener.OnItemClick(adapterPosition)

            }

            }
        }


    }


