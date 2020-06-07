package com.example.myappstore

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.myappstore.database.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class EntryDbTest {


private lateinit var entryDao: EntryDao
    private lateinit var database: EntryDatabase



    @Before
fun createDb(){

val context = InstrumentationRegistry.getInstrumentation().targetContext


database = Room.inMemoryDatabaseBuilder(context,EntryDatabase::class.java)
    .allowMainThreadQueries()
    .build()
entryDao = database.entryDao

    }



    @After
    @Throws(IOException::class)
    fun closeDb(){

database.close()
    }




@Test
@Throws(Exception::class)
fun insertdata(){

    database.entryDao.getEntries()
}


}