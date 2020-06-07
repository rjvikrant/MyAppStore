package com.example.myappstore.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL ="https://itunes.apple.com/us/rss/newfreeapplications/limit=2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface PlayStoreApiService{
    @GET("json")
    fun getAppData(): Deferred<AppData>//Call<AppData>//Deferred<List<AppData>>


}



object PlayStoreApi{

    val retrofitService : PlayStoreApiService by lazy {
        retrofit.create(PlayStoreApiService::class.java)
    }
}