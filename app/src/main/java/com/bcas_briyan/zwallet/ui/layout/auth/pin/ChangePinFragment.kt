package com.bcas_briyan.zwallet.ui.layout.auth.pin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentChangePinBinding
import com.bcas_briyan.zwallet.model.request.SetPinRequest
import com.bcas_briyan.zwallet.ui.widget.LoadingDialog
import com.bcas_briyan.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class ChangePinFragment : Fragment() {
    private lateinit var binding: FragmentChangePinBinding
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: PinViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePinBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog(requireActivity())
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

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.btnContinue.setOnClickListener {
            val request = SetPinRequest(binding.pin1.text.toString() + binding.pin2.text.toString()
                    + binding.pin3.text.toString() + binding.pin4.text.toString() + binding.pin5.text.toString()
                    + binding.pin6.text.toString())

            viewModel.setPin(request).observe(viewLifecycleOwner) {
                when (it.state) {
                    State.LOADING -> {
                        loadingDialog.start("Processing your request ;)")
                    }
                    State.SUCCESS -> {
                        if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                            loadingDialog.stop()
                            Navigation.findNavController(view).navigate(R.id.action_changePinFragment_to_createPinSuccessFragment)
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