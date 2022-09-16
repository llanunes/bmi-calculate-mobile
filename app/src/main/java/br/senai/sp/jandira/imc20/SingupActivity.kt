package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.senai.sp.jandira.imc20.model.User

class SingupActivity : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editWeight: EditText
    lateinit var editHeight: EditText
    lateinit var editButtonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        supportActionBar!!.hide()

        editName = findViewById(R.id.name)
        editEmail = findViewById(R.id.email)
        editPassword = findViewById(R.id.password)
        editWeight = findViewById(R.id.weight)
        editHeight= findViewById(R.id.height)
        editButtonSave = findViewById(R.id.button_login)

        editButtonSave.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {
       val user = User()
        user.id = 1
        user.name = editName.text.toString()
        user.email = editEmail.text.toString()
        user.password = editPassword.text.toString()
        user.weight = editWeight.text.toString().toInt()
        user.height = editHeight.text.toString().toDouble()

        val dados = getSharedPreferences("dados", MODE_PRIVATE)
        val editor = dados.edit()

        editor.putInt("id", user.id);
        editor.putString("name", user.name);
        editor.putString("email", user.email);
        editor.putString("password", user.password);
        editor.putInt("weight", user.weight);
        editor.putFloat("height", user.height.toFloat());

        if (editor.commit()) {
            Toast.makeText(this, "Usuario gravado com sucesso!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Não foi possível gravar o usuário!", Toast.LENGTH_SHORT).show()
        }
    }
}

