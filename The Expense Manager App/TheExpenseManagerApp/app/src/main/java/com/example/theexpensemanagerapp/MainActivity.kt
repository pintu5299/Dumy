package com.example.theexpensemanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.theexpensemanagerapp.database.ExpenseDatabase
import com.example.theexpensemanagerapp.repository.ExpenseRepository
import com.example.theexpensemanagerapp.viewmodel.ExpenseViewModel
import com.example.theexpensemanagerapp.viewmodel.ExpenseViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel(){
        val expenseRepository = ExpenseRepository(ExpenseDatabase(this))
        val viewModelProviderFactory = ExpenseViewModelFactory(application, expenseRepository)
        expenseViewModel = ViewModelProvider(this, viewModelProviderFactory)[ExpenseViewModel::class.java]

    }

}