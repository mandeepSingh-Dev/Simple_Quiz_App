package com.mandeep.quizapp

data class Items(
    val question:String,
    val options:List<String>,
    val correctAnswer:String,
    val imageId:Int,
    val color:Int

)
