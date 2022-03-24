package com.bcas_briyan.zwallet.ui.layout.auth.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.FragmentSearchReceiverBinding
import com.bcas_briyan.zwallet.ui.adapter.ContactAdapter
import com.bcas_briyan.zwallet.utils.State
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class SearchReceiverFragment : Fragment() {
    private lateinit var binding: FragmentSearchReceiverBinding
    private lateinit var contactAdapter: ContactAdapter
    private val viewModel: TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchReceiverBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareData()

        binding.backBtn.setOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun prepareData() {
        this.contactAdapter = ContactAdapter(listOf()) { getContact, view ->
            viewModel.setSelectedContact(getContact)
            Navigation.findNavController(view).navigate(R.id.action_recieverFragment_to_inputAmountFragment)
        }

        binding.recyclerContact.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }

        viewModel.getContact().observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.apply {
                        loadingIndicator.visibility = View.GONE
                        recyclerContact.visibility = View.VISIBLE
                    }
                    if (it.resource?.status == HttpsURLConnection.HTTP_OK){
                        this.contactAdapter.apply {
                            addData(it.resource.data!!)
                            binding.numberOfContact.setText(" Contacts Found " + itemCount)
                            notifyDataSetChanged()
                        }
                    } else {
                        Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                State.LOADING -> {
                    binding.apply {
                        loadingIndicator.visibility = View.VISIBLE
                        recyclerContact.visibility = View.GONE
                    }
                }
                State.ERROR -> {
                    binding.apply {
                        loadingIndicator.visibility = View.VISIBLE
                        recyclerContact.visibility = View.GONE
                    }
                    Toast.makeText(context, it.resource?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}