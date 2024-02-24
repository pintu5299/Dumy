package com.example.theexpensemanagerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.theexpensemanagerapp.databinding.ExpenseLayoutBinding
import com.example.theexpensemanagerapp.fragments.HomeFragmentDirections
import com.example.theexpensemanagerapp.model.Expense

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>()
{
    class ExpenseViewHolder(val itemBinding: ExpenseLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private var differCallback = object : DiffUtil.ItemCallback<Expense>()
    {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.expenseAmount == newItem.expenseAmount &&
                    oldItem.expenseCategory == newItem.expenseCategory &&
                    oldItem.expenseDescription == newItem.expenseDescription
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this,  differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            ExpenseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int
    {
        return differ.currentList.size
    }
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = differ.currentList[position]

        holder.itemBinding.expenseAmount.text = currentExpense.expenseAmount.toString()
        holder.itemBinding.expennseCate.text = currentExpense.expenseCategory
        holder.itemBinding.expenseDes.text = currentExpense.expenseDescription

        holder.itemView.setOnClickListener{
            val directions = HomeFragmentDirections.actionHomeFragmentToEditExpenseFragment(currentExpense)
            it.findNavController().navigate(directions)

        }


      }
}