package com.example.theexpensemanagerapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.theexpensemanagerapp.model.Expense
import com.example.theexpensemanagerapp.repository.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenseViewModel(app: Application, private val expenseRepository: ExpenseRepository): AndroidViewModel(app) {


    fun addExpense(expense: Expense) =
     viewModelScope.launch {
          expenseRepository.insertExpense(expense)

     }

     fun deleteExpense(expense: Expense) =
          viewModelScope.launch {
               expenseRepository.deleteExpense(expense)

          }

     fun updateExpense(expense: Expense) =
          viewModelScope.launch {
               expenseRepository.updateExpense(expense)

          }

     fun getAllExpense() = expenseRepository.getAllExpense()






}