package br.com.concrete.database

import androidx.room.TypeConverter
import br.com.concrete.database.entity.RepositoryEntity
import br.com.concrete.model.Repository

class Converters {

    @TypeConverter
    fun fromRepositoryEntityToRepository(repositoryEntity: RepositoryEntity): Repository {

        val modelRepository = Repository(
            id = repositoryEntity.id,
            name = repositoryEntity.name.toString(),
            description =  repositoryEntity.description,
            username = repositoryEntity.username,
            userImageUrl = repositoryEntity.userImageUrl,
            forkCount = 0,
            starCount = 0
        )

        return modelRepository
    }

}