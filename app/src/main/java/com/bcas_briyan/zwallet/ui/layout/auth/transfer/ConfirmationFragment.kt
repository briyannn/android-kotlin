package com.bcas_briyan.zwallet.ui.layout.auth.transfer

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentConfirmationBinding
import com.bcas_briyan.zwallet.utils.BASE_URL
import com.bcas_briyan.zwallet.utils.Helper.formatPrice
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class ConfirmationFragment : Fragment() {
    private lateinit var binding: FragmentConfirmationBinding
    private val viewModel: TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareData()

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.btnContinue.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_confirmationFragment_to_pinConfirmationFragment)
        }
    }

    fun prepareData() {
        viewModel.selectedContact().observe(viewLifecycleOwner) {
            binding.textUsername.text = it.name
            binding.textPhone.text = it.phone
            Glide.with(binding.imageContact)
                .load(BASE_URL + it.image)
                .apply(
                    RequestOptions.circleCropTransform()
                        .placeholder(R.drawable.ic_baseline_broken_image_24)
                )
                .into(binding.imageContact)
        }

        viewModel.getTransferParam().observe(viewLifecycleOwner) {
            binding.textAmount.formatPrice(it.amount.toString())

            if (it.notes.isNullOrEmpty()) {
                binding.textNotes.text = ""
            } else {
                binding.textNotes.text = it.notes
            }

            // format date
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mma")
                val answer =  current.format(formatter)
                binding.textDate.text = answer
            } else {
                val date = Date()
                val formatter = SimpleDateFormat("MMM dd, yyyy - HH:mma")
                val answer = formatter.format(date)
                binding.textDate.text = answer
            }
        }

        /*viewModel.getBalance().observe(viewLifecycleOwner) {
            binding.apply {
                textBalanced.formatPrice(it.resource?.data?.get(0)?.balance.toString())
            }
        }*/
    }
}