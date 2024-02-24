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
 // it is extending RecyclerView.Adapter and its manage the expense item in recyclerView
class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>()
{
    class   ExpenseViewHolder(val itemBinding: ExpenseLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private var differCallback = object : DiffUtil.ItemCallback<Expense>()
    {
        // this methods check item of id properties same or not if id will be same than its return true otherwise return false
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
             return oldItem.id == newItem.id &&
                    oldItem.expenseAmount == newItem.expenseAmount &&
                    oldItem.expenseCategory == newItem.expenseCategory &&
                    oldItem.expenseDescription == newItem.expenseDescription
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return newItem == oldItem
        }
    }

                 // It is a utility class and its calculate recycler View item difference
    //here this keyword use for call the RecyclerView.adapter
    // differ callback is a object of DiffUtil.itemCallback its comparison of item
    val differ = AsyncListDiffer(this,  differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            ExpenseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent,false )
        )

    }

    override fun getItemCount(): Int
    {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = differ.currentList[position]
       // it is represent itemView of instance
        holder.itemBinding.expenseAmount.text = currentExpense.expenseAmount
        holder.itemBinding.expennseCate.text = currentExpense.expenseCategory
        holder.itemBinding.expenseDes.text = currentExpense.expenseDescription
      // holder is represent recycler View of specific ItemView
        holder.itemView.setOnClickListener{
            val directions = HomeFragmentDirections.actionHomeFragmentToEditExpenseFragment(currentExpense)
            it.findNavController().navigate(directions) 

        }

      }
}