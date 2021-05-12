package br.com.concrete.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.concrete.database.entity.RepositoryEntity

@Dao
interface FavoritesDao {

    @Insert
    fun insert(repository: RepositoryEntity)

    @Query("SELECT * FROM repo_table")
    fun getAllFavorites(): List<RepositoryEntity>

    @Query("SELECT * FROM repo_table WHERE id = :id")
    fun getFavById(id: Int): RepositoryEntity?

}