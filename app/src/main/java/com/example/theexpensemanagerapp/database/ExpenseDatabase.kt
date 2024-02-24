package com.example.theexpensemanagerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theexpensemanagerapp.model.Expense

@Database
    (entities = [Expense::class], version = 1)
  abstract class ExpenseDatabase: RoomDatabase()
{
      abstract fun getExpanseDao() : ExpenseDao
      companion object {
          //volatile annotation ensures that changes made by one thread are immediately visible to other thread
          @Volatile
          private var instance: ExpenseDatabase? = null
          // only one thread can execute the code inside the block at a time
          private val LOCK = Any()
         // it allow to we to create an instance of
          operator fun invoke(context:  Context) = instance ?:
          synchronized(LOCK){
              instance ?:
              createDatabase(context).also{
                  instance = it
              }
          }


          private fun createDatabase(context: Context) =
              // its a method of room database
              Room.databaseBuilder (
                  context.applicationContext,
                  ExpenseDatabase::class.java,
                  "expense_db"
              ).build()
          }
      }



