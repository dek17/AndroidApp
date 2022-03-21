package com.example.pertemuan6_71190423

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentB : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bFragment = inflater.inflate(R.layout.fragment_b, container, false)
        val buttonB = bFragment.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.buttonB)

        buttonB.setOnClickListener {
            val start = Intent(context, MainActivityThree::class.java )
            startActivity(start)
        }
        return bFragment
    }
}