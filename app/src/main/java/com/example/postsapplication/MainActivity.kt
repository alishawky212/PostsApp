package com.example.postsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.postsapplication.master.view.MasterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MasterFragment())
            .commitAllowingStateLoss()
    }
}
