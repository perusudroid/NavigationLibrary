package com.androidsolutions.fragmentnavigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidsolutions.fragmentnavigation.databinding.ItemCustomerBinding

class CustomerAdapter(val callback: (CustomerModel, Int,Int) -> Unit) : RecyclerView.Adapter<CustomerAdapter.VH>() {

    var customerList: ArrayList<CustomerModel>? = null
        set(value) {
            field = value
            notifyItemChanged(0, (field?.size ?: 1) - 1)
        }

    fun updateAt(currentPos: Int, it: CustomerModel?) {
        customerList?.set(currentPos, it!!)
        notifyItemChanged(currentPos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(customerList?.get(position))
    }

    override fun getItemCount() = customerList?.size ?: 0


    inner class VH(private val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback.invoke(binding.root.tag as CustomerModel, adapterPosition,1)
            }
            binding.ivEdit.setOnClickListener {
                callback.invoke(binding.root.tag as CustomerModel, adapterPosition,2)
            }
        }

        fun bind(model: CustomerModel?) {
            with(binding) {
                root.tag = model
                tvName.text = model?.name
                tvProduct.text = model?.product
                tvDate.text = model?.date
            }
        }
    }
}