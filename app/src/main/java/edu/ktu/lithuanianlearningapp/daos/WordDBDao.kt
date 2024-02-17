package edu.ktu.lithuanianlearningapp.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import edu.ktu.lithuanianlearningapp.models.WordDB
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDBDao {
    @Query("SELECT * FROM wordsDB")
    fun getAllWords(): Flow<List<WordDB>>
    @Insert
    suspend fun insertAll(vararg wordDB: WordDB)
    @Insert
    suspend fun insert(wordDB: WordDB)
    @Delete
    suspend fun delete(wordDB: WordDB)
}