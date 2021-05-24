package br.com.concrete.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import br.com.concrete.database.entity.RepositoryEntity

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repository: RepositoryEntity)

    @Delete
    suspend fun remove(repository: RepositoryEntity)

    @Query("SELECT id FROM repo_table")
    suspend fun getIdAllFavorites(): List<Int>

    @Query("SELECT * FROM repo_table")
    fun getAllFavorites(): PagingSource<Int, RepositoryEntity>

    @Query("DELETE FROM repo_table")
    suspend fun clearRepos()
}