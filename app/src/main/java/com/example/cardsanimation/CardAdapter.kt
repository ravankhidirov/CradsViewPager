package com.example.cardsanimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2

class CardAdapter(private val cardsList:ArrayList<Int>,private val viewPager2: ViewPager2)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    val FIRST = 0
    val SECOND = 1


    class FirstCardsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cardsFrameLayout:FrameLayout = itemView.findViewById(R.id.cardsFrameLayout)
        val europe:ImageView = itemView.findViewById(R.id.europe)
        val travel:ImageView = itemView.findViewById(R.id.travel)
        val main:ImageView = itemView.findViewById(R.id.main)
    }


    class CardViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val cardView :ImageView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return if (viewType == FIRST){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_first,parent,false)
            FirstCardsViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
            CardViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FirstCardsViewHolder){

            holder.europe.setImageResource(cardsList[3])
            holder.travel.setImageResource(cardsList[2])
            holder.main.setImageResource(cardsList[1])
        }else if (holder is CardViewHolder){
            holder.cardView.setImageResource(cardsList[position])
        }
    }

    override fun getItemCount(): Int {
        return cardsList.size
    }



    override fun getItemViewType(position: Int): Int {
        return if (position == 0){
            FIRST
        }else {
            SECOND
        }
    }

}