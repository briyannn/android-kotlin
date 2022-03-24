package com.bcas_briyan.zwallet.ui.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bcas_briyan.zwallet.ui.layout.auth.AuthActivity
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.ui.layout.main.MainActivity
import com.bcas_briyan.zwallet.utils.KEY_LOGGED_IN
import com.bcas_briyan.zwallet.utils.PREFS_NAME

@SuppressLint("CustomSplashScreen")
class SpalshScreenActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        if (prefs.getBoolean(KEY_LOGGED_IN, false)) {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2500)
        } else {
            Handler().postDelayed({
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}