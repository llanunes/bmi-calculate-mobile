package br.senai.sp.jandira.imc20.utils

import android.content.Context
import kotlin.math.pow

fun getBmi(weight:Int,height:Float): Float{
    return weight / height.pow(2)
}

fun getStatusBmi(bmi: Double, context: Context): String {

 var status = ""
    if(bmi <= 18.5){
        return "abaixo do peso."
    }else if (bmi <= 25){
        return "com peso ideal. Parabéns!"
    }else if (bmi <= 30){
        return "levemente acima do peso."
    }else if(bmi <= 35 ){
        return "com obesidade grau I."
    }else if(bmi <= 40){
        return "com obesidade grau II"
    }else{
        return "com obesidade grau III. Cuidado!"
    }
}