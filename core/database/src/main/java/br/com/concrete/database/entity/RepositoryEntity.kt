package br.com.concrete.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.concrete.model.Repository

@Entity(tableName = "repo_table")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val username: String,
    val userImageUrl: String
) {

    companion object {
        fun RepositoryEntity.toModel(): Repository {
            return Repository(
                id = id,
                name = name,
                description = description,
                starCount = starCount,
                forkCount = forkCount,
                username = username,
                userImageUrl = userImageUrl,
            )
        }

        fun fromModel(repository: Repository): RepositoryEntity {
            return RepositoryEntity(
                id = repository.id,
                name = repository.name,
                description = repository.description,
                starCount = repository.starCount,
                forkCount = repository.forkCount,
                username = repository.username,
                userImageUrl = repository.userImageUrl,
            )
        }
    }
}