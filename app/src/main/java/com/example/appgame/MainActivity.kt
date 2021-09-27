package com.example.appgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var numberGussButton: Button
    private lateinit var guessThePharaseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberGussButton=findViewById(R.id.btNumbersGame)
        numberGussButton.setOnClickListener { startGame(NumberGuss()) }
        guessThePharaseButton=findViewById(R.id.btGuessThePhrase)
        numberGussButton.setOnClickListener { startGame(PharsGuss()) }

        title = "Main activity"



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_numbers_game -> {
                startGame(NumberGuss())
                return true
            }
            R.id.mi_guess_the_phrase -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun startGame(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}