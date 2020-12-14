package com.example.nightmodetest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(Button(this).also {
            it.text = this::class.java.simpleName
            it.setOnClickListener {
                this.startActivity(Intent(this, SubActivity::class.java))
            }
        })
    }
}
