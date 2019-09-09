package com.e.myworkplace.networks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.e.myworkplace.db.AppDataBase
import com.e.myworkplace.db.Task
import com.e.myworkplace.db.WordExample
import kotlinx.coroutines.launch

/* * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.*/




class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val taskrepository: CategoryRepository
    val allTasks: LiveData<List<Task>>

    init {
        val taskDao = AppDataBase.getInstaince(application,viewModelScope).wordExampleDao()
        taskrepository = CategoryRepository(taskDao)
        allTasks = taskrepository.allWord
    }
    fun insert(words: List<Task>) = viewModelScope.launch {
        for (task in words) {
            taskrepository.insert(task)
        }
    }
}
