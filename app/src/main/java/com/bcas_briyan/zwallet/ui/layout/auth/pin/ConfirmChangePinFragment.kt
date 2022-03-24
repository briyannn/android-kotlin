package com.bcas_briyan.zwallet.ui.layout.auth.pin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentConfirmChangePinBinding

class ConfirmChangePinFragment : Fragment() {
    private lateinit var binding: FragmentConfirmChangePinBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmChangePinBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pin1.doOnTextChanged { text, start, before, count ->
            if (count >= 1) {
                binding.pin2.requestFocus()
            }
        }
        binding.pin2.doOnTextChanged { text, start, before, count ->
            if (count >= 1) {
                binding.pin3.requestFocus()
            }
        }
        binding.pin3.doOnTextChanged { text, start, before, count ->
            if (count >= 1) {
                binding.pin4.requestFocus()
            }
        }
        binding.pin4.doOnTextChanged { text, start, before, count ->
            if (count >= 1) {
                binding.pin5.requestFocus()
            }
        }
        binding.pin5.doOnTextChanged { text, start, before, count ->
            if (count >= 1) {
                binding.pin6.requestFocus()
            }
        }
    }
}