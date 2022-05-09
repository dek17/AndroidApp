package com.example.pertemuan9_71190423

import android.app.ActionBar
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.core.view.size
import androidx.core.widget.addTextChangedListener
import org.w3c.dom.Text
import java.util.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    var SharedPref : SharedPreferences? = null
    var SPedit : SharedPreferences.Editor? = null
    lateinit var locale: Locale
    var currentLang = " "
    var currentLangStr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)








        SharedPref = getSharedPreferences("SP", MODE_PRIVATE)
        SPedit = SharedPref?.edit()
        val LastClick = SharedPref?.getInt("LastClick",0)


        currentLang = intent.getStringExtra(currentLangStr).toString()

        val loginView = findViewById<TextView>(R.id.LoginView)
        loginView.setText(SharedPref?.getString("login","").toString())






        if(SharedPref?.getBoolean("isLogin", false) == true){
            setContentView(R.layout.activity_menu)
            val btnLogout = findViewById<AppCompatButton>(R.id.logoutButton)
            btnLogout.setOnClickListener {
                isLogout()

            }

            val gysnc = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.gysncSwitch)
            gysnc.isChecked = SharedPref!!.getBoolean("Gsync", false)
            gysnc.setOnCheckedChangeListener { compoundButton, b ->
                SPedit?.putBoolean("Gsync", b)
                SPedit?.commit()

            }

            val frameRate = findViewById<EditText>(R.id.fps)
            frameRate.setText(SharedPref?.getString("fps","").toString())
            frameRate.addTextChangedListener{it ->
                SPedit?.putString("fps", it.toString())
                SPedit?.commit()

            }

            val rtx = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.rtx)
            rtx.isChecked = SharedPref!!.getBoolean("rtx", false)
            rtx.setOnCheckedChangeListener { compoundButton, b ->
                SPedit?.putBoolean("rtx", b)
                SPedit?.commit()

            }

            val physX = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.physx)
            physX.isChecked = SharedPref!!.getBoolean("physX", false)
            physX.setOnCheckedChangeListener { compoundButton, b ->
                SPedit?.putBoolean("physX", b)
                SPedit?.commit()

            }

            val latencyMode = findViewById<androidx.appcompat.widget.SwitchCompat>(R.id.lowLatencyMode)
            latencyMode.isChecked = SharedPref!!.getBoolean("llm", false)
            latencyMode.setOnCheckedChangeListener { compoundButton, b ->
                SPedit?.putBoolean("llm", b)
                SPedit?.commit()

            }

            val languages = resources.getStringArray(R.array.Languages)
            val spinner = findViewById<Spinner>(R.id.spinner)
            val text = findViewById<TextView>(R.id.txtView)


            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,languages)
            spinner.adapter = adapter
            spinner.setSelection(LastClick!!)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, Id: Long) {
                    text.text = languages.get(position)
                    text.setText(SharedPref?.getString("language","").toString())
                    text.addTextChangedListener{it ->
                        SPedit?.putString("language", it.toString())
                        SPedit?.commit()
                    }
                    SPedit?.putInt("LastClick",position)
                    SPedit?.commit()

                    when (position) {
                        0 -> setLocale("en")
                        1 -> setLocale("id")

                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    text.text = "Please select Language"
                }
            }

            var MIN = 50
            var STEP = 5
            var MAX = 300
            val seek = findViewById<SeekBar>(R.id.seekBar)
            seek.max = (MAX - MIN) / STEP


            val seekText = findViewById<TextView>(R.id.seekText)
            val sizeFont = findViewById<TextView>(R.id.size)


            val creater = findViewById<TextView>(R.id.Creater)
            val language = findViewById<TextView>(R.id.language)
            val display = findViewById<TextView>(R.id.display)
            val rtxfeature = findViewById<TextView>(R.id.rtxfeature)
            val fpsText = findViewById<TextView>(R.id.textFps)



            val progress = SharedPref?.getInt("progress",0)
            sizeFont.text = progress.toString()
            seekText.setTextSize(progress!!.toFloat())
            seek.setProgress(progress!!)

            creater.setTextSize(progress!!.toFloat())
            language.setTextSize(progress!!.toFloat())
            display.setTextSize(progress!!.toFloat())
            rtxfeature.setTextSize(progress!!.toFloat())
            gysnc.setTextSize(progress!!.toFloat())
            rtx.setTextSize(progress!!.toFloat())
            fpsText.setTextSize(progress!!.toFloat())
            physX.setTextSize(progress!!.toFloat())
            latencyMode.setTextSize(progress!!.toFloat())
            frameRate.setTextSize(progress!!.toFloat())







            seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress : Int, fromUser: Boolean) {
//                    val progressCustom = MIN + (progress * STEP)

                    seekText.setTextSize(progress.toFloat())
                    creater.setTextSize(progress.toFloat())
                    language.setTextSize(progress.toFloat())
                    display.setTextSize(progress.toFloat())
                    rtxfeature.setTextSize(progress.toFloat())
                    gysnc.setTextSize(progress!!.toFloat())
                    rtx.setTextSize(progress!!.toFloat())
                    fpsText.setTextSize(progress!!.toFloat())
                    physX.setTextSize(progress!!.toFloat())
                    latencyMode.setTextSize(progress!!.toFloat())

                    SPedit?.putInt("progress",progress)
                    SPedit?.commit()

                    sizeFont.text = progress.toString()
                    sizeFont.setText(SharedPref?.getString("fontSize","").toString())
                    sizeFont.addTextChangedListener{it ->
                        SPedit?.putString("fontSize", it.toString())
                        SPedit?.commit()
                    }





                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {


                }

            })




        }

        else {
            setContentView(R.layout.activity_main)
            val username = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.userName)
            val password = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.password)
            val login = findViewById<AppCompatButton>(R.id.buttonLogin)
            when(LastClick){
                0 -> setLocale("en")
                1 -> setLocale("id")

            }
            login.setOnClickListener{
                isLogin(username.text.toString(), password.text.toString())
            }
        }
    }

    fun setLocale(LocalName: String) {
        if(LocalName != currentLang) {
            locale = Locale(LocalName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            res.updateConfiguration(conf, dm)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(currentLangStr, LocalName)
            startActivity(intent)
            finish()

        }

        else{}
    }



    fun isLogin(userName : String, passcode : String){
        if(userName.equals("Deka") && passcode.equals("nana123")){
            SPedit?.putBoolean("isLogin", true)
            SPedit?.commit()
            val i = Intent(this, MainActivity:: class.java)
            startActivity(i)
            finish()

        }else{
            Toast.makeText(this,"Username & Password Not Valid", Toast.LENGTH_LONG).show()
        }


    }


    fun isLogout(){

        SPedit?.putBoolean("isLogin", false)
        SPedit?.commit()
        val i = Intent(this, MainActivity:: class.java)
        startActivity(i)
        finish()

    }
}