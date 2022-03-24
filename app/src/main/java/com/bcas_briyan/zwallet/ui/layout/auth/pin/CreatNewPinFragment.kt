package com.bcas_briyan.zwallet.ui.layout.auth.pin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentCreatNewPinBinding
import com.bcas_briyan.zwallet.model.request.SetPinRequest
import com.bcas_briyan.zwallet.utils.State
import com.bcas_briyan.zwallet.ui.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class CreateNewPinFragment : Fragment() {
    private lateinit var binding: FragmentCreatNewPinBinding
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: PinViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog = LoadingDialog(requireActivity())
        binding = FragmentCreatNewPinBinding.inflate(layoutInflater)
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

        binding.pin6.addTextChangedListener {
            if (binding.pin6.text.length > 0) {
                binding.btnConfirm.setBackgroundResource(R.drawable.background_button_auth_active)
                binding.btnConfirm.setTextColor(Color.parseColor("#FFFFFF"))
            } else if (binding.pin6.text.length < 1) {
                binding.btnConfirm.setBackgroundResource(R.drawable.background_button_auth)
                binding.btnConfirm.setTextColor(Color.parseColor("#9DA6B5"))
            }
        }

        binding.btnConfirm.setOnClickListener {
            val request = SetPinRequest(binding.pin1.text.toString() + binding.pin2.text.toString()
                    + binding.pin3.text.toString() + binding.pin4.text.toString() + binding.pin5.text.toString()
                    + binding.pin6.text.toString())

            viewModel.setPin(request).observe(viewLifecycleOwner){
                when (it.state){
                    State.LOADING -> {
                        loadingDialog.start("Creating your PIN!")
                    }
                    State.SUCCESS -> {
                        if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                            loadingDialog.stop()
                            Navigation.findNavController(view).navigate(R.id.action_createPinFragment_to_createPinSuccessFragment2)
                        } else {
                            Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    State.ERROR -> {
                        loadingDialog.stop()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}