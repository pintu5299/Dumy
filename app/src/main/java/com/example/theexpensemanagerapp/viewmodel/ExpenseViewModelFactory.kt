package com.example.theexpensemanagerapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.theexpensemanagerapp.repository.ExpenseRepository

class ExpenseViewModelFactory(val app: Application, private val expenseRepository: ExpenseRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpenseViewModel(app, expenseRepository) as T
    }
}