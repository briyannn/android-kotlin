package com.bcas_briyan.zwallet.ui.auth.forgot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentChangeNewPasswordBinding

class ChangeNewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangeNewPasswordBinding
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           binding = FragmentChangeNewPasswordBinding.inflate(layoutInflater)
           return binding.root
    }
}