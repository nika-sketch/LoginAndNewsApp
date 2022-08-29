package ge.nlatsabidze.newsapplication.domain.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.nlatsabidze.newsapplication.data.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(marketItem: List<Article>)

    @Query("SELECT * FROM Article")
    fun fetchArticle(): MutableList<Article>

    @Query("DELETE FROM Article")
    fun deleteArticle()
}
