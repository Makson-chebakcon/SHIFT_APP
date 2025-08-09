package com.example.shift_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shift_app.data.model.UserModel
import com.example.shift_app.data.repositories.UserRepositories

@Database(entities = [UserModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userRepositories(): UserRepositories

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "converter_database"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            println("Database created")
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}