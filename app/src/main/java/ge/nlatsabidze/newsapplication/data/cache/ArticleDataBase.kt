package ge.nlatsabidze.newsapplication.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.domain.cache.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}
