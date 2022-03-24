package com.bcas_briyan.zwallet.ui.layout.auth.password

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentChangePasswordBinding
import com.bcas_briyan.zwallet.model.request.ChangePasswordRequest
import com.bcas_briyan.zwallet.ui.layout.SpalshScreenActivity
import com.bcas_briyan.zwallet.ui.layout.auth.pin.PinViewModel
import com.bcas_briyan.zwallet.ui.widget.LoadingDialog
import com.bcas_briyan.zwallet.utils.KEY_LOGGED_IN
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import com.bcas_briyan.zwallet.utils.State
import javax.net.ssl.HttpsURLConnection

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var preferences: SharedPreferences
    private val viewModel: PinViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePasswordBinding.inflate(layoutInflater)
        preferences = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newPassword.addTextChangedListener {
            if (binding.newPassword.text.length >= 8) {
                binding.btnContinue.setBackgroundResource(R.drawable.background_button_auth_active)
                binding.btnContinue.setTextColor(Color.parseColor("#FFFFFF"))
            } else if (binding.newPassword.text.length < 8) {
                binding.btnContinue.setBackgroundResource(R.drawable.background_button_auth)
                binding.btnContinue.setTextColor(Color.parseColor("#9DA6B5"))
            }
        }

        binding.btnContinue.setOnClickListener {
            if (binding.newPassword.text.toString() == binding.currentPassword.text.toString()) {
                return@setOnClickListener
            } else {
                val response = viewModel.changePassword(
                    ChangePasswordRequest(
                        old_password = binding.currentPassword.text.toString(),
                        new_password = binding.newPassword.text.toString())
                )

                response.observe(viewLifecycleOwner) {
                    when (it.state) {
                        State.LOADING -> {
                            loadingDialog.start("Processing your password")
                        }
                        State.SUCCESS -> {
                            if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                                Toast.makeText(context, "Password changed", Toast.LENGTH_SHORT)
                                    .show()

                                with(preferences.edit()) {
                                    putBoolean(KEY_LOGGED_IN, false)
                                    apply()
                                }

                                loadingDialog.stop()
                                val intent = Intent(activity, SpalshScreenActivity::class.java)
                                startActivity(intent)
                                activity?.finish()
                            } else {
                                loadingDialog.stop()
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

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }
}