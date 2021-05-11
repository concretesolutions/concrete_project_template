package br.com.concrete.ghpulls.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.concrete.model.Repository
import retrofit2.HttpException
import java.io.IOException

class LoadReposPagingSource(
    private val loadReposAction: suspend (pageNumber: Int) -> List<Repository>,
) : PagingSource<Int, Repository>() {
    override fun getRefreshKey(state: PagingState<Int, Repository>) = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val pageNumber = params.key ?: FIRST_PAGE
            val results = loadReposAction(pageNumber)

            LoadResult.Page(
                data = results,
                prevKey = null,
                nextKey = pageNumber + 1
            )
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}
