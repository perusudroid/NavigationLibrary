package com.androidsolutions.fragmentnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.androidsolutions.fragmentnavigation.databinding.FragmentCustomerDetailBinding


class CustomerDetailFragment : Fragment() {

    private val binding by lazy { FragmentCustomerDetailBinding.inflate(layoutInflater) }
    private val customerModel by lazy { arguments?.getSerializable("customerModel") as CustomerModel? }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCustomerDetails()
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(
                R.id.action_customerDetailFragment_to_customerEditFragment,
                bundleOf("customerModel" to customerModel),
                NavOptions.Builder().setPopUpTo(R.id.customerDetailFragment, true).build()
            )
        }
    }

    private fun bindCustomerDetails() {
        customerModel?.run {
            binding.tvName.text = getString(R.string.name_s, name)
            binding.tvProduct.text = getString(R.string.product_s, product)
            binding.tvDate.text = getString(R.string.date_s, date)
            binding.tvDesc.text = desc
        }
    }
}