package com.androidsolutions.fragmentnavigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidsolutions.fragmentnavigation.databinding.FragmentCustomerListBinding

class CustomerListFragment : Fragment() {

    private val binding by lazy { FragmentCustomerListBinding.inflate(layoutInflater) }
    private val customerAdapter by lazy { CustomerAdapter(::callback) }
    private var currentPos = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        binding.rvCustomers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = customerAdapter
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        customerAdapter.customerList = prepareCustomerList()
    }

    private fun subscribeToObservers() {
        findNavController().currentBackStackEntry?.savedStateHandle?.run {
            getLiveData<CustomerModel>("customerResult").observe(viewLifecycleOwner){
                customerAdapter.updateAt(currentPos, it)
                remove<CustomerModel>("customerResult")
            }
        }
    }

    private fun callback(model: CustomerModel, pos : Int,type : Int) {
        currentPos = pos
        if(type == 2){
            //customer edit
            findNavController().navigate(
                R.id.action_customerListFragment_to_customerEditFragment,
                bundleOf("customerModel" to model)
            )
        }else{
            //customer detail
            findNavController().navigate(
                R.id.action_customerListFragment_to_customerDetailFragment,
                bundleOf("customerModel" to model)
            )
        }
    }

    private fun prepareCustomerList() = arrayListOf(
        CustomerModel("Franklin", getString(R.string.dummy_6), "Product 1", "01/01/2022"),
        CustomerModel("Joseph", getString(R.string.dummy_6), "Product 2", "01/02/2022"),
        CustomerModel("Albert", getString(R.string.dummy_6), "Product 3", "01/03/2022"),
        CustomerModel("Don", getString(R.string.dummy_6), "Product 4", "01/04/2022"),
        CustomerModel("Dominic", getString(R.string.dummy_6), "Product 5", "01/05/2022"),
    )
}