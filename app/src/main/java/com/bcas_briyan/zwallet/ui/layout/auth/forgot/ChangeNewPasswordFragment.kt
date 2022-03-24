package com.bcas_briyan.zwallet.ui.layout.auth.forgot

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentChangeNewPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeNewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangeNewPasswordBinding
       override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           binding = FragmentChangeNewPasswordBinding.inflate(layoutInflater)
           return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.addTextChangedListener {
            if (binding.inputPassword2.text.length > 3) {
                binding.btnConfirm.setBackgroundResource(R.drawable.background_button_auth_active)
                binding.btnConfirm.setTextColor(Color.parseColor("#FFFFFF"))
            } else if (binding.inputPassword2.text.length <= 3) {
                binding.btnConfirm.setBackgroundResource(R.drawable.background_button_auth)
                binding.btnConfirm.setTextColor(Color.parseColor("#9DA6B5"))
            }
        }
    }
}