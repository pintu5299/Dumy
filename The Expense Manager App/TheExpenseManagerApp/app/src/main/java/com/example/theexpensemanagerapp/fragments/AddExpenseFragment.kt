package com.example.theexpensemanagerapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.theexpensemanagerapp.MainActivity
import com.example.theexpensemanagerapp.R
import com.example.theexpensemanagerapp.databinding.FragmentAddExpenseBinding
import com.example.theexpensemanagerapp.model.Expense
import com.example.theexpensemanagerapp.viewmodel.ExpenseViewModel

class AddExpenseFragment : Fragment(R.layout.fragment_add_expense), MenuProvider
{
    private var addExpenseBinding: FragmentAddExpenseBinding? = null
    private val binding get() = addExpenseBinding!!
    private lateinit var expenseViewModel: ExpenseViewModel
    private  lateinit var addExpenseView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        addExpenseBinding = FragmentAddExpenseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        expenseViewModel = (activity as MainActivity).expenseViewModel
        addExpenseView = view
    }


   private  fun saveExpense(view: View)
   {
       val expenseAmount = binding.addExpenseAmount.text.toString().trim()
       val expenseCategory = binding.addExpenseCategory.text.toString().trim()
       val expenseDescription = binding.addExpenseDesc.text.toString().trim()
       if(expenseAmount.isNotEmpty () && expenseCategory.isNotEmpty() && expenseDescription.isNotEmpty()){
           val expense = Expense(0, expenseAmount, expenseCategory, expenseDescription)
           expenseViewModel.addExpense(expense)
           Toast.makeText(addExpenseView.context, "Expense Data Saved", Toast.LENGTH_SHORT).show()
           view.findNavController().popBackStack(R.id.homeFragment, false)
       }
       else
           Toast.makeText(addExpenseView.context, "Please all full field", Toast.LENGTH_SHORT).show()
       }
  
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_expense, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.saveMenu  -> {
                saveExpense(addExpenseView)
                true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        addExpenseBinding = null
    }
}



