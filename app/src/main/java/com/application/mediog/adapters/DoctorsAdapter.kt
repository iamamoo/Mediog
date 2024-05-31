package com.application.mediog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.application.mediog.R
import com.application.mediog.models.DoctorModel
import com.application.mediog.utils.OnItemClickListener

class DoctorsAdapter(private var list : ArrayList<DoctorModel>, private val textView: ConstraintLayout,private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<DoctorsAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.textView24)
        val special : TextView = itemView.findViewById(R.id.textView26)
        val starOne : ImageView = itemView.findViewById(R.id.starOne)
        val startwo : ImageView = itemView.findViewById(R.id.startwo)
        val starthree : ImageView = itemView.findViewById(R.id.starthree)
        val starfour : ImageView = itemView.findViewById(R.id.starfour)
        val starfive : ImageView = itemView.findViewById(R.id.starfive)
        val rating : TextView = itemView.findViewById(R.id.textView27)
        val reviews : TextView = itemView.findViewById(R.id.textView28)
        val card : CardView = itemView.findViewById(R.id.card)
    }

    fun updateList(newList : ArrayList<DoctorModel>) {
        this.list = newList
        if (newList.isEmpty()) {
            textView.visibility = View.VISIBLE
            notifyDataSetChanged()
        } else {
            textView.visibility = View.GONE
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_list_sample,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.name.text = model.doctorName!!
        holder.special.text = model.specialist!!

        when(model.star) {
            1 -> {
                holder.starOne.visibility = View.VISIBLE
                holder.startwo.visibility = View.GONE
                holder.starthree.visibility = View.GONE
                holder.starfour.visibility = View.GONE
                holder.starfive.visibility = View.GONE
            }
            2 -> {
                holder.starOne.visibility = View.VISIBLE
                holder.startwo.visibility = View.VISIBLE
                holder.starthree.visibility = View.GONE
                holder.starfour.visibility = View.GONE
                holder.starfive.visibility = View.GONE
            }
            3 -> {
                holder.starOne.visibility = View.VISIBLE
                holder.startwo.visibility = View.VISIBLE
                holder.starthree.visibility = View.VISIBLE
                holder.starfour.visibility = View.GONE
                holder.starfive.visibility = View.GONE
            }
            4 -> {
                holder.starOne.visibility = View.VISIBLE
                holder.startwo.visibility = View.VISIBLE
                holder.starthree.visibility = View.VISIBLE
                holder.starfour.visibility = View.VISIBLE
                holder.starfive.visibility = View.GONE
            }
            5 -> {
                holder.starOne.visibility = View.VISIBLE
                holder.startwo.visibility = View.VISIBLE
                holder.starthree.visibility = View.VISIBLE
                holder.starfour.visibility = View.VISIBLE
                holder.starfive.visibility = View.VISIBLE
            }
        }

        holder.card.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }

        holder.rating.text = model.rating.toString()
        holder.reviews.text = "(${model.reviews} views)"
    }
}