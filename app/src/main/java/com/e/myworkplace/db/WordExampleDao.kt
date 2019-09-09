package com.e.myworkplace.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordExampleDao {
    @Query("SELECT * from task")
    fun getAlphabetizedWords(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task)

    @Query("DELETE FROM task")
    suspend fun deleteAll()
}
