package br.com.concrete.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.concrete.database.entity.RepositoryEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RepositoryEntity)

    @Query("SELECT * FROM repo_table")
    fun getAllFavorites(): List<RepositoryEntity>

    @Query("SELECT * FROM repo_table WHERE id = :id")
    fun getFavById(id: Int): RepositoryEntity?

    @Query("DELETE FROM repo_table")
    suspend fun clearRepos()

}