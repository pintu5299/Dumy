package com.example.theexpensemanagerapp.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.theexpensemanagerapp.MainActivity
import com.example.theexpensemanagerapp.R
import com.example.theexpensemanagerapp.databinding.FragmentEditExpenseBinding
import com.example.theexpensemanagerapp.model.Expense
import com.example.theexpensemanagerapp.viewmodel.ExpenseViewModel

class EditExpenseFragment : Fragment(R.layout.fragment_edit_expense), MenuProvider {

    private var editExpenseBinding: FragmentEditExpenseBinding? = null
    private  val binding get() = editExpenseBinding!!

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var currentExpense: Expense


    private val args: EditExpenseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editExpenseBinding = FragmentEditExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        expenseViewModel = (activity as MainActivity).expenseViewModel
        currentExpense = args.expense!!

        binding.editExpenseAmount.setText(currentExpense.expenseAmount)
        binding.editExpenseCategory.setText(currentExpense.expenseCategory)
        binding.editExpenseDesc.setText(currentExpense.expenseDescription)

        binding.editExpenseFab.setOnClickListener {
            val expenseAmount = binding.editExpenseAmount.text.toString().trim()
            val expenseCategory = binding.editExpenseCategory.text.toString().trim()
            val expenseDescription = binding.editExpenseDesc.text.toString().trim()

            if (expenseAmount.isNotEmpty() && expenseCategory.isNotEmpty() && expenseDescription.isNotEmpty()){
                val expense = Expense(currentExpense.id, expenseAmount, expenseCategory, expenseDescription)
                expenseViewModel.updateExpense(expense)
                view.findNavController().popBackStack(R.id.homeFragment, false)
            }
            else{

                Toast.makeText(context, "please all required full field", Toast.LENGTH_SHORT).show()
            }

            }

        }
    private fun deleteExpense()
    {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Expense")
            setMessage("Do yo want to delete this expense?")
            setPositiveButton("Delete" ){_,_ ->
                expenseViewModel.deleteExpense(currentExpense)
                Toast.makeText(context, "Expense Deleted", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel", null)
        }
            .create().show()


    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_expense, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu -> {
                deleteExpense()
                true
            }else  -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editExpenseBinding = null
    }

}