package com.kimboo.examples.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.kimboo.examples.ui.detail.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_activity_info_section.*
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

/**
 * Displays the Repository details. There's not much to do here since we are not fetching anything
 * from any API. That's why this Activity doesn't have a ViewModel.
 */
class DetailActivity : AppCompatActivity() {

    // region Variables declaration
    @Inject
    lateinit var viewModelProvider: MyViewModelFactory
    lateinit var viewModel: DetailViewModel

    private val viewInjector: ExampleViewInjector
        get() = DaggerExampleViewInjector.builder()
            .baseSubComponent(getBaseSubComponent())
            .build()
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        viewInjector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelProvider)
            .get(DetailViewModel::class.java)

        observeMessageChanges()
        observeStateChanges()

        setupToolbarView()

        val squareRepositoryId = intent.extras?.getString(ARG_BUNDLE_REPOSITORY_ID)
        if (squareRepositoryId != null) {
            viewModel.getSquareRepository(squareRepositoryId)
        }
    }

    private fun observeStateChanges() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is DetailViewModel.State.Success -> {
                    onShowSquareRepository(it.squareRepository)
                }
                is DetailViewModel.State.Error -> {
                    onDisplayErrorMessage()
                }
            }
        })
    }

    private fun onDisplayErrorMessage() {
        Snackbar.make(
            mainActivityExamplesSwipeRefreshLayout,
            getString(R.string.detail_activity_error_message),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun onShowSquareRepository(squareRepository: SquareRepository) {
        supportActionBar?.title = squareRepository.name
        activityDetailNameTextView.text = squareRepository.name
        activityDetailStarsTextView.text = squareRepository.stars.toString()
        invalidateOptionsMenu()
    }

    private fun observeMessageChanges() {
        viewModel.message.observe(this, Observer {
            when (it) {
                is DetailViewModel.StateMessage.UnknownSaveBookmarkError -> {
                    onShowErrorMessage()
                }
            }
        })
    }

    private fun onShowErrorMessage() {
        Snackbar.make(
            activityDetailContainer,
            getString(R.string.detail_activity_error_saving_bookmark),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun setupToolbarView() {
        setSupportActionBar(detailActivityExamplesToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_repository_detail, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItemBookmark = menu?.findItem(R.id.action_bookmark)
        val isBookmarked = viewModel.isBookmarked()
        menuItemBookmark?.icon = if (isBookmarked) {
            // Set marked icon
            getDrawable(R.drawable.ic_bookmark_white_24dp)
        } else {
            // Set unmarked icon
            getDrawable(R.drawable.ic_bookmark_border_white_24dp)
        }
        // Initially the bookmark menu item is invisible until we load its value from cache
        menuItemBookmark?.isVisible = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_bookmark -> {
                viewModel.bookmarkRepository()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        private const val ARG_BUNDLE_REPOSITORY_ID = "arg_bundle_repository_id"

        fun getStartIntent(context: Context, squareRepositoryId: String): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_BUNDLE_REPOSITORY_ID, squareRepositoryId)
            }
        }
    }
}