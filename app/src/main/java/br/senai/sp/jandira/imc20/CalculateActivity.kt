package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBinding
import br.senai.sp.jandira.imc20.utils.getBmi
import br.senai.sp.jandira.imc20.utils.getStatusBmi

class CalculateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        loadUserProfile()

        binding.buttonCalculate.setOnClickListener {
            calculateBmi()
        }
    }
    private fun calculateBmi(){
        val openResult = Intent(this, ResultActivity::class.java)
        val data = getSharedPreferences("dados", MODE_PRIVATE)
        var editor = data.edit()
        var height = 0.0f

        if (validateInputDatas()){
            if (binding.editTextHeight.text.isEmpty()){
                height = data.getFloat("height", 0.0f)
            } else {
                height = binding.editTextHeight.text.toString().toFloat()
            }
            var weight = binding.editTextWeight.text.toString().toInt()

            var bmi = getBmi(weight, height).toDouble()
            var statusBmi = getStatusBmi(bmi, this)

            editor.putFloat("height", height)
            editor.putInt("weight", weight)
            editor.commit()

            openResult.putExtra("bmi", bmi)
            openResult.putExtra("statusBmi", statusBmi)

            startActivity(openResult)
        } else {
            Toast.makeText(this, "Invalid datas", Toast.LENGTH_SHORT).show()
        }
    }
        private fun validateInputDatas() : Boolean{
            if (binding.editTextWeight.text.isEmpty()) {
                binding.editTextWeight.error = "Required field!"
                return false
            } else {
                return true
            }
        }

        private fun loadUserProfile() {
            val data = getSharedPreferences("dados", MODE_PRIVATE)

            binding.textViewName.text = data.getString("name", "")
            binding.textViewEmail.text = data.getString("email", "")
            binding.textViewWeight.text = "Weight: ${data.getInt("weight", 0)}Kg"
            binding.textViewHeight.text =  "Height: ${data.getFloat("height", 0.0F)}m"
        }
}

