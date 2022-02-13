package com.mironov.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.mironov.newsapp.repository.retrofit.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle(Article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllArticles(list: List<Article>)

    @Update
    suspend fun updateArticle(Article: Article)

    @Delete
    suspend fun deleteArticle(Article: Article)

    @Query("DELETE FROM Article")
    fun resetTable( )

    @RawQuery(observedEntities = [Article::class])
    fun readArticlesByTest(query: SupportSQLiteQuery): LiveData<List<Article?>>

    @Query("SELECT * FROM Article")
    fun readAllArticles(): LiveData<List<Article?>>

}