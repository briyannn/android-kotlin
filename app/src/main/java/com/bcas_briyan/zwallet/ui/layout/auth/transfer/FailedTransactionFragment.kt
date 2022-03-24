package com.bcas_briyan.zwallet.ui.layout.auth.transfer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentFailedTransactionBinding
import com.bcas_briyan.zwallet.ui.layout.main.MainActivity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FailedTransactionFragment : Fragment() {
    private lateinit var binding: FragmentFailedTransactionBinding
    private val viewModel: TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFailedTransactionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeBtn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_failedTransactionFragment_to_homeFragment)
        }

        prepareData(view)
    }

    private fun prepareData(view: View) {
        viewModel.getTransferParam().observe(viewLifecycleOwner) {
            binding.textAmount.text = it.amount.toString()
            binding.textNotes.text = it.notes

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

            Handler().postDelayed({
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }, 2000)
        }

        viewModel.selectedContact().observe(viewLifecycleOwner) {
            binding.textUsername.text = it.name
            binding.textPhone.text = it.phone
        }

        viewModel.getBalance().observe(viewLifecycleOwner) {
            binding.textBalanced.text = it.resource?.data?.get(0)?.balance.toString()
        }
    }
}