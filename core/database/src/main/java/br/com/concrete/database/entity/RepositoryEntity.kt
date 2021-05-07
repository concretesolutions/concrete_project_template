package br.com.concrete.database.entity

import androidx.room.Entity

@Entity
data class RepositoryEntity(
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val username: String,
    val userImageUrl: String
)