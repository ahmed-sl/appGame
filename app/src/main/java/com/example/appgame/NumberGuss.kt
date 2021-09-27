package com.example.appgame

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class NumberGuss : AppCompatActivity() {
    private lateinit var clRoot: ConstraintLayout
    private lateinit var guessField: EditText
    private lateinit var guessButton: Button
    private lateinit var messages: ArrayList<String>
    private lateinit var tvPrompt: TextView
    private lateinit var rvMessages : RecyclerView

    private var answer = 0
    private var guesses = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.numberguss)

        answer = Random.nextInt(10)

        clRoot = findViewById(R.id.clRoot)
        messages = ArrayList()

        tvPrompt = findViewById(R.id.tvPrompt)

        rvMessages=findViewById(R.id.rvMessages)

        rvMessages.adapter = MessageAdapter(this, messages)
        rvMessages.layoutManager = LinearLayoutManager(this)

        guessField = findViewById(R.id.etGuessField)
        guessButton = findViewById(R.id.btGuessButton)

        guessButton.setOnClickListener { addMessage() }
    }

    private fun addMessage() {
        val msg = guessField.text.toString()
        if (msg.isNotEmpty()) {
            if (guesses > 0) {
                if (msg.toInt() == answer) {
                    disableEntry()
                    showAlertDialog("You win!\n\nPlay again?")
                } else {
                    guesses--
                    messages.add("You guessed $msg")
                    messages.add("You have $guesses guesses left")
                }
                if (guesses == 0) {
                    disableEntry()
                    messages.add("You lose - The correct answer was $answer")
                    messages.add("Game Over")
                    showAlertDialog("You lose...\nThe correct answer was $answer.\n\nPlay again?")
                }
            }
            guessField.text.clear()
            guessField.clearFocus()
            rvMessages.adapter?.notifyDataSetChanged()
        } else {
            Snackbar.make(clRoot, "Please enter a number", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun disableEntry() {
        guessButton.isEnabled = false
        guessButton.isClickable = false
        guessField.isEnabled = false
        guessField.isClickable = false
    }

    private fun showAlertDialog(title: String) {

        val dialogBuilder = AlertDialog.Builder(this)


        dialogBuilder.setMessage(title)

            .setCancelable(false)

            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                this.recreate()
            })

            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("Game Over")

        alert.show()
    }
}