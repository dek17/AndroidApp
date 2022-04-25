package com.example.pertemuan8_71190423

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        viewPager = findViewById(R.id.Pager)
        val listFragment : ArrayList<Fragment> = arrayListOf(FragmentLogin(), FragmentRegister(), FragmentSignUp())
        val pagerAdapter = PagerAdapter(this, listFragment)
        viewPager.adapter = pagerAdapter



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginButton -> viewPager.currentItem = 0
            R.id.register -> viewPager.currentItem = 1
            R.id.signupIcon -> viewPager.currentItem = 2
        }
        return true
    }

    class PagerAdapter(val fa : FragmentActivity, val listFragment : ArrayList<Fragment>): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment[position]
    }
}

