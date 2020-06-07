package com.example.myappstore.detailed_info

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myappstore.R
import com.example.myappstore.database.DbEntry
import com.example.myappstore.database.EntryDao
import kotlinx.coroutines.*

class DetailedViewModel(val id: Int, val application: Application, val dataSource: EntryDao) :
    ViewModel() {



    val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )
    private var _entryId = MutableLiveData<Int>()
    val entryId: LiveData<Int>
        get() = _entryId


    private var _moreinfo = MutableLiveData<String>()
    val moreInfo: LiveData<String>
        get() = _moreinfo


    fun moreInfoClick() {
        _moreinfo.value = entry.value?.link?.attributes?.href
    }

    fun DonemoreInfoClick() {
        _moreinfo.value = null
    }




    private var _entry = MutableLiveData<DbEntry>()
    val entry: LiveData<DbEntry>
        get() = _entry


    init {
        _entryId.value = id
        coroutineScope.launch {
            _entry.value = getEntryFromDb(id)
        }
    }

    suspend fun getEntryFromDb(id: Int): DbEntry {
        return withContext(Dispatchers.IO) {
            dataSource.getEntry(id)
        }
    }

    val idString = Transformations.map(_entry) {
        it.entryName.label

    }

    val priceString = Transformations.map(_entry) {
        it?.let {
        application.applicationContext.getString(
            when (it.entryPrice.attributes.amount > 0) {
                true -> R.string.price_string_fromat
                false -> R.string.price_string_free

            }, it.entryPrice.attributes.amount
        )
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
