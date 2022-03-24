package com.bcas_briyan.zwallet.ui.layout.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}