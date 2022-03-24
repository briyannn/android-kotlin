package com.bcas_briyan.zwallet.ui.layout.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.bcas_briyan.zwallet.databinding.FragmentTopUpBinding
import com.bcas_briyan.zwallet.ui.layout.auth.transfer.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopUpFragment : Fragment() {
    private lateinit var binding: FragmentTopUpBinding
    private val viewModel: TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        prepareData(view)
    }

    private fun prepareData(view: View) {
        viewModel.getBalance().observe(viewLifecycleOwner) {
            binding.virtualAccount.text = it.resource?.data?.get(0)?.phone
        }
    }
}