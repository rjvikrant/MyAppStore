package com.example.myappstore.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testdefinelabs.database.converters.Converters

@Dao
interface EntryDao {

    @Query("select * from DbEntry")
    fun getEntries(): LiveData<List<DbEntry>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entry: List<DbEntry>)


    @Query("select * from DbEntry where entryid IN(:id)")
    fun getEntry(id: Int): DbEntry

    @Query("delete from DbEntry")
    fun removeAllData()

}


@Database(
    entities = [DbEntry::class], version = 1
)
@TypeConverters(Converters::class)
abstract class EntryDatabase : RoomDatabase() {
    abstract val entryDao: EntryDao

}


private lateinit var INSTANCE: EntryDatabase


fun getDatabase(context: Context): EntryDatabase {

    synchronized(EntryDatabase::class.java) {


        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                EntryDatabase::class.java,
                "entry"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    return INSTANCE
}