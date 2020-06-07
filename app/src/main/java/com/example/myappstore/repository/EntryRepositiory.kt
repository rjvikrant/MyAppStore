package com.example.myappstore.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.myappstore.database.DbEntry
import com.example.myappstore.database.EntryDatabase
import com.example.myappstore.network.PlayStoreApi
import com.example.myappstore.network.PlayStoreApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EntryRepositiory(private  val database: EntryDatabase) {
    private val TAG: String = "EntryRepository"

val entries: LiveData<List<DbEntry>> = database.entryDao.getEntries()


suspend fun refreshEntry(){
    withContext(Dispatchers.IO){
        Log.d(TAG,"Refresh Entry id called")
        val entryList = PlayStoreApi.retrofitService.getAppData().await()
        database.entryDao.removeAllData()
        database.entryDao.insertEntries(entryList.feed.entry)

    }

    }

}