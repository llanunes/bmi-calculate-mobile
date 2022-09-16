package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()


        binding.textSignUp.setOnClickListener {
            val abrirSingupActivity = Intent(this, SingupActivity::class.java)
            startActivity(abrirSingupActivity)
        }
        binding.button.setOnClickListener {
            login()
        }
    }

    private fun login() {

        if (validar()) {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val dados = getSharedPreferences("dados", MODE_PRIVATE)
            
            val emailSp = dados.getString("email", "Email não encontrado")
            val passwordSp = dados.getString("password", "")
            
            if(email == emailSp && password == passwordSp){
                val openCalculate = Intent(this, CalculateActivity::class.java)
                startActivity(openCalculate)
            } else{
                Toast.makeText(this, "Autentication failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validar(): Boolean {
        if (binding.editTextTextEmailAddress.text.isEmpty()) {
            Toast.makeText(this, "E-mail is requires!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (binding.editTextTextPassword.text.isEmpty()) {
            binding.editTextTextPassword.error = "Usúario ou senha incorretos"
            return false
        }
        return true
    }
}