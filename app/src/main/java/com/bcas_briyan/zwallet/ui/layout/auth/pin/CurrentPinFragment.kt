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
import com.bcas_briyan.zwallet.databinding.FragmentCurrentPinBinding
import com.bcas_briyan.zwallet.ui.widget.LoadingDialog
import com.bcas_briyan.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class CurrentPinFragment : Fragment() {
    private lateinit var binding: FragmentCurrentPinBinding
    private val viewModel: PinViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var pin: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentPinBinding.inflate(layoutInflater)
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

        binding.btnContinue.setOnClickListener {
            prepareData(view)
        }

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    fun prepareData(view: View) {
        pin = binding.pin1.text.toString() + binding.pin2.text.toString() +
                binding.pin3.text.toString() + binding.pin4.text.toString() +
                binding.pin5.text.toString() + binding.pin6.text.toString()

        viewModel.checkPin(pin).observe(viewLifecycleOwner) {
            when (it.state) {
                State.LOADING -> {
                    loadingDialog.start("Checking your current PIN")
                }
                State.SUCCESS -> {
                    if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                        loadingDialog.stop()
                        Navigation.findNavController(view).navigate(R.id.action_currentPinFragment_to_changePinFragment)
                    } else {
                        loadingDialog.stop()
                        Toast.makeText(context, "Invalid current PIN!", Toast.LENGTH_SHORT)
                    }
                }
                State.ERROR -> {
                    loadingDialog.stop()
                    Toast.makeText(context, "Invalid current PIN!", Toast.LENGTH_SHORT)
                }
            }
        }
    }
}