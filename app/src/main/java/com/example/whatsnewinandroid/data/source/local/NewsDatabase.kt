package com.example.whatsnewinandroid.data.source.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whatsnewinandroid.data.responses.NewsArticle

/**
 * News room database...
 */
@Database(
    entities = [NewsArticle::class],
    exportSchema = false,
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticlesDao

    companion object {

        private const val databaseName = "news-db"

        fun buildDefault(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, databaseName)
                .build()

        @VisibleForTesting
        fun buildTest(context: Context) =
            Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java)
                .build()
    }
}