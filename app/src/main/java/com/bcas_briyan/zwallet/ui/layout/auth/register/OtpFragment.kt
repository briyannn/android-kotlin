package com.bcas_briyan.zwallet.ui.layout.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentOtpBinding
import com.bcas_briyan.zwallet.ui.layout.auth.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpFragment : Fragment() {
    private lateinit var binding: FragmentOtpBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var otp: String
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareData(view)
    }

    private fun prepareData(view: View) {
        otp = binding.otp1.text.toString() + binding.otp2.text.toString() + binding.otp3.text.toString() +
                binding.otp4.text.toString() + binding.otp5.text.toString() + binding.otp6.text.toString()

        viewModel.login(email, password).observe(viewLifecycleOwner){
            binding.apply {
                email = it.resource?.data?.email.toString()
            }
        }
    }
}