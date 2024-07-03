package com.example.taskmanagersummerpractice.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanagersummerpractice.model.database.DAO.TagsDAO
import com.example.taskmanagersummerpractice.model.database.DAO.TasksDAO
import com.example.taskmanagersummerpractice.model.database.entities.TagsDbEntity
import com.example.taskmanagersummerpractice.model.database.entities.TaskTagsCrossRefDbEntity
import com.example.taskmanagersummerpractice.model.database.entities.TasksDbEntity

@Database(entities = [TasksDbEntity::class, TagsDbEntity::class, TaskTagsCrossRefDbEntity::class], version = 1)
abstract class ROOMDatabase : RoomDatabase(){
    abstract fun tagsDAO(): TagsDAO
    abstract fun tasksDAO(): TasksDAO

    companion object {
        private var INSTANCE: ROOMDatabase? = null
        fun getInstance(context: Context): ROOMDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ROOMDatabase::class.java,
                        "Task_database"

                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}