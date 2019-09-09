package com.e.myworkplace.networks

import androidx.lifecycle.LiveData
import com.e.myworkplace.db.Task
import com.e.myworkplace.db.WordExample
import com.e.myworkplace.db.WordExampleDao

class CategoryRepository(private val wordExampleDao: WordExampleDao) {

    val allWord: LiveData<List<Task>> = wordExampleDao.getAlphabetizedWords()

    suspend fun insert(wordExample: Task) {
        wordExampleDao.insert(wordExample)
    }
}
