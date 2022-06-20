package com.mandeep.quizapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandeep.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    lateinit var binding:ActivityMainBinding

    lateinit var linearLayoutMnagaer:LinearLayoutManager
    var positionn = 0
    var itemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()


        val question1 = getString(R.string.question1)
        val question2 = getString(R.string.question2)
        val question3 = getString(R.string.question3)
        val question4 = getString(R.string.question4)
        val question5 = getString(R.string.question5)

        val question1OOptions:List<String>  = resources.getStringArray(R.array.question1_Options).asList()
        val question2OOptions:List<String>  = resources.getStringArray(R.array.question2_Options).asList()
        val question3OOptions:List<String>  = resources.getStringArray(R.array.question3_Options).asList()
        val question4OOptions:List<String>  = resources.getStringArray(R.array.question4_Options).asList()
        val question5OOptions:List<String>  = resources.getStringArray(R.array.question5_Options).asList()

        val arraylist = ArrayList<Items>()


        arraylist.add(Items(question1,question1OOptions,question1OOptions[1],R.drawable.javaimage,R.color.color1))
        arraylist.add(Items(question2,question2OOptions,question2OOptions[0],R.drawable.indainflag,R.color.color2))
        arraylist.add(Items(question3,question3OOptions,question3OOptions[2],R.drawable.androidicno,R.color.color3))
        arraylist.add(Items(question4,question4OOptions,question4OOptions[3],R.drawable.kotlinimage,R.color.color4))
        arraylist.add(Items(question5,question5OOptions,question5OOptions[0],R.drawable.indainflag,R.color.color4))


        val myadapater = MyAdapter(this,arraylist)
        linearLayoutMnagaer =object :LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false){
            override fun canScrollVertically(): Boolean {
               // return super.canScrollVertically()
                return false
            }
            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }

        binding.recyclerVIew.layoutManager = linearLayoutMnagaer
        binding.recyclerVIew.adapter = myadapater

        itemCount = linearLayoutMnagaer.itemCount

        binding.seekBar.max = itemCount-1

        myadapater.setOnClickCustomListener(object:MyAdapter.OnCustomClickListener{
            override fun onCustomClick(position: Int,itemView: View) {
                positionn = position

                positionn++
                binding.seekBar.progress = positionn
                if(positionn != itemCount) {

                    linearLayoutMnagaer.scrollToPosition(positionn)
                    binding.recyclerVIew.animation = AnimationUtils.loadAnimation(this@MainActivity,R.anim.alpha)
                    /*val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.alpha)
                    imagevIew?.startAnimation(animation)*/

                }
            }
        })

        binding.seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val tempPos = positionn
                positionn = progress
                linearLayoutMnagaer.scrollToPosition(positionn)

                if(positionn > tempPos) {
                    binding.recyclerVIew.animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.alpha)
                }else{
                    binding.recyclerVIew.animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.alpha_prev)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

    }

    override fun onBackPressed() {

        if(positionn > 0) {
            if(positionn == itemCount)
            {
                positionn -= 2
            }
            else{
                positionn--
            }
            binding.seekBar.progress = positionn
            linearLayoutMnagaer.scrollToPosition(positionn)
            binding.recyclerVIew.animation = AnimationUtils.loadAnimation(this@MainActivity,R.anim.alpha_prev)

        }
        else{
            super.onBackPressed()
        }

    }
}