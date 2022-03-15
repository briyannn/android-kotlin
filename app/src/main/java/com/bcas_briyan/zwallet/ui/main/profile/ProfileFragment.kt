package com.bcas_briyan.zwallet.ui.main.profile

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentProfileBinding
import com.bcas_briyan.zwallet.ui.SpalshScreenActivity
import com.bcas_briyan.zwallet.ui.main.home.HomeViewModel
import com.bcas_briyan.zwallet.ui.viewModelsFactory
import com.bcas_briyan.zwallet.utils.KEY_LOGGED_IN
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import com.bcas_briyan.zwallet.utils.State
import javax.net.ssl.HttpsURLConnection

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var preferences : SharedPreferences
    private val viewModel: HomeViewModel by viewModelsFactory { HomeViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        preferences = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMyProfile().observe(viewLifecycleOwner){
            when (it.state) {
                State.LOADING -> {

                }
                State.SUCCESS -> {
                    if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                        binding.apply {
                            textUsername.text = it.resource.data?.firstname + " " + it.resource.data?.lastname
                            textPhone.text = it.resource.data?.phone
                        }
                    } else {
                        Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                else -> {

                }
            }
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Logout Confirmation")
                .setMessage("Are you sure wanna logout?")
                .setPositiveButton("Yes") {_, _ ->
                    with(preferences.edit()) {
                        putBoolean(KEY_LOGGED_IN, false)
                        apply()
                    }

                    val intent = Intent(activity, SpalshScreenActivity::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("Cancel") {_, _ ->
                    return@setNegativeButton
                }
                .show()
        }

        binding.btnPersonalInformation.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_personalProfileFragment)
        }
    }
}