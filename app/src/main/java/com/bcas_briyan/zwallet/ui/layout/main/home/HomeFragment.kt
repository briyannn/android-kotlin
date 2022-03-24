package com.bcas_briyan.zwallet.ui.layout.main.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.ui.adapter.TransactionAdapter
import com.bcas_briyan.zwallet.databinding.FragmentHomeBinding
import com.bcas_briyan.zwallet.utils.PREFS_NAME
import com.bcas_briyan.zwallet.utils.Helper.formatPrice
import com.bcas_briyan.zwallet.utils.State
import com.bcas_briyan.zwallet.ui.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = context?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!

        prepareData()

        binding.profileImage.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.topUp.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_topUpFragment)
        }

        binding.transfer.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_recieverFragment)
        }
    }

    private fun prepareData() {
        this.transactionAdapter = TransactionAdapter(listOf())

        binding.recyclerTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }

        viewModel.getInvoice().observe(viewLifecycleOwner) {
            when (it.state) {
                State.LOADING -> {
                    binding.apply {
                        loadingIndicator.visibility = View.VISIBLE
                        recyclerTransaction.visibility = View.GONE
                    }
                }
                State.SUCCESS -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerTransaction.visibility = View.VISIBLE
                    }
                    if (it.resource?.status == HttpsURLConnection.HTTP_OK){
                        this.transactionAdapter.apply {
                            addData(it.resource.data!!)
                            notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                State.ERROR -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerTransaction.visibility = View.VISIBLE
                    }
                    Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getBalance().observe(viewLifecycleOwner) {
            when (it.state) {
                State.LOADING -> {
                    binding.apply {
                        loadingIndicator.visibility = View.VISIBLE
                        recyclerTransaction.visibility = View.GONE
                    }
                }
                State.SUCCESS -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerTransaction.visibility = View.VISIBLE
                    }
                    if (it.resource?.status == HttpsURLConnection.HTTP_OK) {
                        binding.apply {
                            textUserBalance.formatPrice(it.resource.data?.get(0)?.balance.toString())
                            textUserName.text = it.resource.data?.get(0)?.name
                            textUserPhone.text = it.resource.data?.get(0)?.phone
                        }
                    } else {
                        Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                State.ERROR -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerTransaction.visibility = View.VISIBLE
                    }
                    Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}