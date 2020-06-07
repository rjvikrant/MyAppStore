package com.example.myappstore.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myappstore.database.getDatabase
import com.example.myappstore.network.PlayStoreApi
import com.example.myappstore.repository.EntryRepositiory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class HomeViewModel(application: Application) : ViewModel() {


    private val entryRepository = EntryRepositiory(getDatabase(application))


    val entryList = entryRepository.entries


    private val viewModelJob = SupervisorJob()

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response





    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _eventNetworkError = MutableLiveData<Boolean>()
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


    private var _isNetworkErrorShown = MutableLiveData<Boolean>()
    val isNetWorkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _ProgressbarEvent = MutableLiveData<Boolean>()
    val ProgressbarEvent: LiveData<Boolean>
        get() = _ProgressbarEvent


    init {
        _eventNetworkError.value = false
        entryList.value?.isEmpty().let {
        refreshDataFromRepository()
            _ProgressbarEvent.value =true
        }

    }

    private fun refreshDataFromRepository() {

        coroutineScope.launch {

            try {
                entryRepository.refreshEntry()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                _ProgressbarEvent.value =false
            } catch (e: Exception) {


                if (entryList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
                _ProgressbarEvent.value =false

            }


        }

    }


    fun onNetworkShown() {

        _isNetworkErrorShown.value = true
    }

    private fun getAppStoreData() {
        coroutineScope.launch {

            /*    PlayStoreApi.retrofitService.getAppData().enqueue(object :retrofit2.Callback<AppData>{
                     override fun onFailure(call: Call<AppData>, t: Throwable) {
                         _response.value= t.message
                     }

                     override fun onResponse(call: Call<AppData>, response: Response<AppData>) {
                         _response.value= response.body()?.feed?.entry?.size.toString()
                     }


                 }

                 )*/


            var getEntryDeferred = PlayStoreApi.retrofitService.getAppData()


            try {
                var listResult = getEntryDeferred.await()
                _response.value = "data retrived ${listResult.feed.entry.size} cool"
            } catch (e: Exception) {

                _response.value = "failure"
            }


        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
