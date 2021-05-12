package br.com.concrete.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val username: String,
    val userImageUrl: String
)