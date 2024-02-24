package com.example.theexpensemanagerapp.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Expense")
@Parcelize
data class Expense(
    @PrimaryKey(autoGenerate = true )
    val id: Int,
    val expenseAmount: String,
    val expenseCategory: String,
    val expenseDescription: String
): Parcelable






