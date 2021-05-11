package br.com.concrete.ghpulls.ui.repos

import br.com.concrete.ghpulls.ui.repos.vo.RepoBaseVo

interface ReposClickCallback {

    fun onClick(item: RepoBaseVo.RepositoryVo?)

}