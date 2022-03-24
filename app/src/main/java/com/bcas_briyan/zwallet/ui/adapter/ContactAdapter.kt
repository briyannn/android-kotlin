package com.bcas_briyan.zwallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcas_briyan.zwallet.R
import com.bcas_briyan.zwallet.databinding.ItemContactFindreceiverBinding
import com.bcas_briyan.zwallet.model.request.AllContactRequest
import com.bcas_briyan.zwallet.utils.BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

class ContactAdapter(
    private var data: List<AllContactRequest>,
    private var clickListener: (AllContactRequest, View) -> Unit,
): RecyclerView.Adapter<ContactAdapter.ContactAdapterHolder>() {
    private lateinit var contextAdapter: Context

    class ContactAdapterHolder(private val binding: ItemContactFindreceiverBinding): RecyclerView.ViewHolder(binding.root) {
        private val image: ShapeableImageView = binding.imageContact
        private val name: TextView = binding.contactName
        private val phone: TextView = binding.contactNumber

        fun bindData(data: AllContactRequest, onClick: (AllContactRequest, View) -> Unit){
            Glide.with(image).load(BASE_URL +data.image).apply(
                RequestOptions.circleCropTransform().placeholder(R.drawable.ic_baseline_directions_bike_24)
            ).into(image)
            name.text = data.name.toString()
            phone.text = data.phone.toString()

            binding.root.setOnClickListener{
                onClick(data, binding.root)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactAdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        this.contextAdapter = parent.context
        val binding = ItemContactFindreceiverBinding.inflate(inflater, parent, false)
        return ContactAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactAdapterHolder, position: Int) {
        holder.bindData(this.data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun addData(data: List<AllContactRequest>){
        this.data = data
    }
}