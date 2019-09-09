package com.e.myworkplace.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Task::class, WordExample::class],version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun wordExampleDao(): WordExampleDao


    companion object {
        @Volatile
        private var INSTAINCE: AppDataBase? = null

        fun getInstaince(
            context: Context,
            scope: CoroutineScope
        ): AppDataBase {

            return INSTAINCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "data"
                )
                    .fallbackToDestructiveMigration()
//                    .addCallback(WortData(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTAINCE = instance
                //return instaince
                instance
            }
        }

        private class WortData(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTAINCE ?: let { database ->
                    scope.launch {
//                                                populationDatabase(database.ta)
                    }
                }
            }

        }

        suspend fun populationDatabase(wordExampleDao: WordExampleDao) {

        }
    }


}
