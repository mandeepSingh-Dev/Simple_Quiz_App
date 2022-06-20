package com.mandeep.quizapp

import android.content.Context
import android.graphics.Color
import android.telephony.RadioAccessSpecifier
import android.text.Layout
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MyAdapter(val context: Context, val arrayList: ArrayList<Items>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     val view = LayoutInflater.from(context).inflate(R.layout.item,parent,false)
       val animation =  AnimationUtils.loadAnimation(context,R.anim.alpha)
       // view.animation = animation
        Log.d("39t9n4g","dfonf")
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = arrayList.get(position)

        holder.textview.text = item.question

        holder.imageVIew.setImageResource(item.imageId)

        val listOptions = item.options
        val optionSize = listOptions.size
        val radioGroup = holder.radioGroup
        for(i in 0..optionSize.minus(1))
        {
            val radiobutton  = radioGroup[i] as RadioButton
            radiobutton.text = listOptions[i]
        }

        holder.button.setOnClickListener {
            onCustomClickListener.onCustomClick(position,holder.itemView)
        }

        holder.submitButton.setOnClickListener {
            val radioButton = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val answer = radioButton?.text

            answer?.let {
                if(it.isNotEmpty() && it.equals(item.correctAnswer))
                {
                    Toast.makeText(context,"correct answer ",Toast.LENGTH_SHORT).show()
                }
                else{
                   val toast=  Toast.makeText(context,"wrong answer ",Toast.LENGTH_SHORT).show()
                }
            }

        }



    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
     val textview: TextView = itemView.findViewById(R.id.questiontextView)
        val button : MaterialButton = itemView.findViewById(R.id.nextButton)
        val imageVIew : ImageView = itemView.findViewById(R.id.imageVieww)
        val radioGroup:RadioGroup= itemView.findViewById(R.id.radioGroup)
        val submitButton :MaterialButton = itemView.findViewById(R.id.submitButton)

    }

    lateinit var onCustomClickListener:OnCustomClickListener
    interface OnCustomClickListener{
        fun onCustomClick(position:Int,itemVIew:View)
    }

    fun setOnClickCustomListener(onCustomClickListener: OnCustomClickListener){
        this.onCustomClickListener = onCustomClickListener
    }
}