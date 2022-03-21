package com.example.pertemuan6_71190423

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentC : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cFragment = inflater.inflate(R.layout.fragment_c, container, false)
        val buttonC = cFragment.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.buttonC)

        buttonC.setOnClickListener {
            val start = Intent(context, MainActivity::class.java )
            startActivity(start)
        }
        return cFragment
    }
}