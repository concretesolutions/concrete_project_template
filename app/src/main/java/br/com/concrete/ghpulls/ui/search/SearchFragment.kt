package br.com.concrete.ghpulls.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import br.com.concrete.ghpulls.R
import br.com.concrete.ghpulls.databinding.FragmentDashboardBinding
import br.com.concrete.ghpulls.ui.repos.ReposAdapter
import br.com.concrete.ghpulls.util.ItemVerticalSpaceDecorator
import br.com.concrete.ghpulls.util.adapter.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by inject()
    private var _binding: FragmentDashboardBinding? = null
    private val reposAdapter = ReposAdapter()
    private val loadState = LoadStateAdapter {
        reposAdapter.retry()
    }


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupViews()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search = binding.searchBar

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                if (query != null) {
                    searchViewModel.search(query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupViews() {
        binding.reposList.adapter = reposAdapter.withLoadStateFooter(loadState)
        binding.reposList.addItemDecoration(
            ItemVerticalSpaceDecorator(R.dimen.default_margin)
        )

            searchViewModel.pager.observe(viewLifecycleOwner){flowPagingData ->
                lifecycleScope.launch{
                    flowPagingData.collectLatest { pagingData ->
                        reposAdapter.submitData(pagingData)
                    }
                }
            }
        }
}
