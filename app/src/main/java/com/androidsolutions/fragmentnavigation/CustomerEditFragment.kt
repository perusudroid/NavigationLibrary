package com.androidsolutions.fragmentnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.androidsolutions.fragmentnavigation.databinding.FragmentCustomerEditBinding

class CustomerEditFragment : Fragment() {

    private val binding by lazy { FragmentCustomerEditBinding.inflate(layoutInflater) }
    private val customerModel by lazy { arguments?.getSerializable("customerModel") as CustomerModel? }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCustomerDetails()
        binding.btnUpdate.setOnClickListener {
            customerModel?.apply {
                name = binding.etName.text.toString()
                product = binding.etProduct.text.toString()
                date = binding.etDate.text.toString()
            }
            findNavController().run {
                previousBackStackEntry?.savedStateHandle?.set("customerResult", customerModel)
                navigateUp()
            }
        }
    }

    private fun bindCustomerDetails() {
        customerModel?.run {
            binding.etName.setText(name)
            binding.etProduct.setText(product)
            binding.etDate.setText(date)
        }
    }

}