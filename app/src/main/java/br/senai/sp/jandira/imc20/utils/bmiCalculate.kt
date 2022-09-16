package br.senai.sp.jandira.imc20.utils

import android.content.Context
import kotlin.math.pow

fun getBmi(weight:Int,height:Float): Float{
    return weight / height.pow(2)
}

fun getStatusBmi(bmi: Double, context: Context): String {

 var status = ""
    if(bmi <= 18.5){
        return "under weight."
    }else if (bmi <= 25){
        return "with ideal weight. Congratulations!"
    }else if (bmi <= 30){
        return "slightly overweight."
    }else if(bmi <= 35 ){
        return "with grade I obesity."
    }else if(bmi <= 40){
        return "with obesity grade II"
    }else{
        return "with grade III obesity. Watch out!"
    }
}