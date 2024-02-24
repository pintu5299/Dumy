package com.example.theexpensemanagerapp.repository

import com.example.theexpensemanagerapp.database.ExpenseDatabase
import com.example.theexpensemanagerapp.model.Expense

class ExpenseRepository(private val db: ExpenseDatabase)
{
    suspend fun insertExpense(expense: Expense) = db.getExpanseDao().insertExpense(expense)
    suspend fun deleteExpense(expense: Expense) = db.getExpanseDao().deleteExpense(expense)
    suspend fun updateExpense(expense: Expense) = db.getExpanseDao().updateExpense(expense)

    fun getAllExpense()  = db.getExpanseDao() .getAllExpenses()


}