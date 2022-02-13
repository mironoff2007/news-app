package com.mironov.newsapp.repository.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.mironov.newsapp.domain.entity.Article
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

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
    fun readArticlesByDate(query: SupportSQLiteQuery): Single<List<Article>>

    @Query("SELECT * FROM Article")
    fun readAllArticles(): Single<List<Article>>

}