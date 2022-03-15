package com.bcas_briyan.zwallet.ui.main.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bcas_briyan.zwallet.databinding.FragmentPersonalProfileBinding
import com.bcas_briyan.zwallet.ui.main.home.HomeViewModel
import com.bcas_briyan.zwallet.ui.viewModelsFactory
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import com.bcas_briyan.zwallet.utils.State
import com.bcas_briyan.zwallet.widget.LoadingDialog
import javax.net.ssl.HttpsURLConnection

class PersonalProfileFragment : Fragment() {
    private lateinit var binding: FragmentPersonalProfileBinding
    private lateinit var preferences : SharedPreferences
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: HomeViewModel by viewModelsFactory { HomeViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalProfileBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!

        prepareData()
    }

    private fun prepareData() {
        viewModel.getMyProfile().observe(viewLifecycleOwner){
            when (it.state) {
                State.LOADING -> {
                    loadingDialog.start("Processing your request ;)")
                }
                State.SUCCESS -> {
                    if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                        binding.apply {
                            firstName.text = it.resource.data?.firstname
                            lastName.text = it.resource.data?.lastname
                            phoneNumber.text = it.resource.data?.phone
                        }
                    } else {
                        Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                    }
                    loadingDialog.stop()
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