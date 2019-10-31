package com.example.kotlincourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fManager : FragmentManager= supportFragmentManager;

        fManager.popBackStack("root_fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        fManager.beginTransaction()
            .replace(R.id.fContainer, ListFragment())
            .addToBackStack("root_fragment")
            .commit()
    }
}
