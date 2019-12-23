package com.kimboo.examples.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.kimboo.core.models.SquareRepository
import com.kimboo.core.utils.MyViewModelFactory
import com.kimboo.core.utils.getBaseSubComponent
import com.kimboo.examples.R
import com.kimboo.examples.di.component.DaggerExampleViewInjector
import com.kimboo.examples.di.component.ExampleViewInjector
import com.kimboo.examples.ui.detail.DetailActivity
import com.kimboo.examples.ui.list.adapter.SquareRepoAdapter
import com.kimboo.examples.ui.list.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

/**
 * Displays a list with all the repositories from Square
 */
class MainActivity : AppCompatActivity(), SquareRepoAdapter.Callback {

    // region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModel: MainViewModel

    private val viewInjector: ExampleViewInjector
        get() = DaggerExampleViewInjector.builder()
            .baseSubComponent(getBaseSubComponent())
            .build()

    private val squareRepoAdapter by lazy {
        SquareRepoAdapter(this, this)
    }
    // endregion

    // region SquareRepoAdapter.Callback implementation
    override fun onRepositoryClicked(repository: SquareRepository) {
        startActivity(DetailActivity.getStartIntent(this, repository))
    }
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewInjector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(MainViewModel::class.java)

        setupRecyclerView()
        setupSwipeRefreshLayout()

        observeIsLoadingChanges()
        observeStateChanges()

        fetchRepositories()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBookmarks()
    }

    private fun observeStateChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is MainViewModel.State.FetchedBookmarks -> {
                    onLoadBookmarks(it.bookmarks)
                }
                is MainViewModel.State.Success -> {
                    onShowRepositoryList(it.list)
                }
                is MainViewModel.State.Error -> {
                    onDisplayErrorMessage()
                }
                is MainViewModel.State.ErrorFetchingBookmarks -> {
                    onDisplayErrorBookmarksMessage()
                }
            }
        })
    }

    private fun onLoadBookmarks(bookmarks: List<String>) {
        squareRepoAdapter.bookmarkedRepositories.clear()
        squareRepoAdapter.bookmarkedRepositories.addAll(bookmarks)
        squareRepoAdapter.notifyDataSetChanged()
    }

    private fun onDisplayErrorBookmarksMessage() {
        Snackbar.make(
            mainActivityExamplesSwipeRefreshLayout,
            getString(R.string.main_activity_error_bookmark_message),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onDisplayErrorMessage() {
        Snackbar.make(
            mainActivityExamplesSwipeRefreshLayout,
            getString(R.string.main_activity_error_message),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowRepositoryList(list: List<SquareRepository>) {
        squareRepoAdapter.repositories.clear()
        squareRepoAdapter.repositories.addAll(list)
        squareRepoAdapter.notifyDataSetChanged()
    }

    private fun observeIsLoadingChanges() {
        viewModel.isLoading.observe(this, Observer {
            mainActivityExamplesSwipeRefreshLayout.isRefreshing = it
        })
    }

    private fun setupSwipeRefreshLayout() {
        mainActivityExamplesSwipeRefreshLayout.setOnRefreshListener {
            fetchRepositories()
        }
    }

    private fun fetchRepositories() {
        viewModel.fetchRepositories()
    }

    private fun setupRecyclerView() {
        mainActivityExamplesRecyclerView.adapter = squareRepoAdapter
    }
}