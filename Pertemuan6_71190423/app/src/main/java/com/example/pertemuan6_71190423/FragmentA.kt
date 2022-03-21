package com.example.pertemuan6_71190423

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val aFragment = inflater.inflate(R.layout.fragment_a, container, false)
        val buttonA = aFragment.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.buttonA)

        buttonA.setOnClickListener {
            val start = Intent(context, MainActivityTwo ::class.java)
            startActivity(start)
        }
        return aFragment
    }
}