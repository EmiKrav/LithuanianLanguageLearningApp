package edu.ktu.lithuanianlearningapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.ktu.lithuanianlearningapp.daos.WordDBDao
import edu.ktu.lithuanianlearningapp.models.WordDB

@Database(entities = arrayOf(WordDB::class), version = 1)
abstract class WordDbDatabase : RoomDatabase() {
    abstract fun WordDBDao(): WordDBDao
    companion object {
        @Volatile
        private var _instance: WordDbDatabase? = null
        fun getInstance(context: Context): WordDbDatabase {
            return _instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDbDatabase::class.java,
                    "worddb-db"
                )
                    .createFromAsset("new-word-db.db")
                    .build()
                _instance = instance
                instance
            }
        }
    }
}