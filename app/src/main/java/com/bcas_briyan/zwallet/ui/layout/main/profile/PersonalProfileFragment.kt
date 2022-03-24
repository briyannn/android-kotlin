package com.bcas_briyan.zwallet.ui.layout.main.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.databinding.FragmentPersonalProfileBinding
import com.bcas_briyan.zwallet.ui.layout.main.home.HomeViewModel
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import com.bcas_briyan.zwallet.utils.State
import com.bcas_briyan.zwallet.ui.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class PersonalProfileFragment : Fragment() {
    private lateinit var binding: FragmentPersonalProfileBinding
    private lateinit var preferences : SharedPreferences
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: HomeViewModel by activityViewModels()

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

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
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
                            textEmail.text = it.resource.data?.email
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