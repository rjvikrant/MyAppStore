package com.example.myappstore.detailed_info

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myappstore.database.EntryDao
import java.lang.IllegalArgumentException
import javax.sql.DataSource

class DetailedViewModelFactory(val id : Int,val application: Application,val dataSource: EntryDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailedViewModel::class.java)) {
            return DetailedViewModel(id,application,dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}