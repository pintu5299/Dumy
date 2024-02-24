package com.example.theexpensemanagerapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.theexpensemanagerapp.MainActivity
import com.example.theexpensemanagerapp.R
import com.example.theexpensemanagerapp.adapter.ExpenseAdapter
import com.example.theexpensemanagerapp.databinding.FragmentHomeBinding
import com.example.theexpensemanagerapp.model.Expense
import com.example.theexpensemanagerapp.viewmodel.ExpenseViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
   // here i declared variable homeBinding which holds instance of fragment
    // fragmentHomeBinding is generated class by data-binding. here i am using null because when onCreateView created
    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var expenseAdapter: ExpenseAdapter
     //    it is the overridden function of fragment class
    // This function inflates the fragment's layout and binds it using data binding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
//    it is the overridden function of fragment class
//    it is use for initialize the fragment view
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // using of this line we are calling parent class(fragment) onViewCreated method and pass view and save instance state
        super.onViewCreated(view, savedInstanceState)
    //  here we are casting the parent activity of the fragment to MainActivity and assign expenseViewmodel
        expenseViewModel = (activity as MainActivity).expenseViewModel
    //it is using for display the expense of recyclerView
        setupHomeRecyclerView()
       // //it is using for setting a click listener on the addExpenseFab when the button is clicked it navigate to the home fragment to addExpenseFragment
        binding.addExpenseFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }
    }

    private fun updateUI(expense: List<Expense>?) {
        if (expense != null) {
            if (expense.isNotEmpty())
            {
                binding.emptyExpenseImage.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
                binding.emptyExpenseImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView()
    {
        expenseAdapter = ExpenseAdapter()
        binding.homeRecyclerView.apply{
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            adapter = expenseAdapter

        }

         // it is using for save the data on home screen
            expenseViewModel.getAllExpense().observe(viewLifecycleOwner) { expense ->
                expenseAdapter.differ.submitList(expense)
                updateUI(expense)
            }
    }
}



