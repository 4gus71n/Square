package com.kimboo.examples.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.kimboo.core.models.SquareRepository
import com.kimboo.core.utils.MyViewModelFactory
import com.kimboo.core.utils.getBaseSubComponent
import com.kimboo.examples.R
import com.kimboo.examples.di.component.DaggerExampleViewInjector
import com.kimboo.examples.di.component.ExampleViewInjector
import com.kimboo.examples.ui.detail.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_activity_info_section.*
import javax.inject.Inject

/**
 * Displays the Repository details. There's not much to do here since we are not fetching anything
 * from any API. That's why this Activity doesn't have a ViewModel.
 */
class DetailActivity : AppCompatActivity() {

    // region Variables declaration
    private var squareRepository: SquareRepository? = null
    private var menuItemBookmark: MenuItem? = null

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

        fetchBundleValus(savedInstanceState)
        setupToolbarView()
        setupTextView()
    }

    private fun setupTextView() {
        activityDetailNameTextView.text = squareRepository?.name ?: ""
        activityDetailStarsTextView.text = squareRepository?.stars.toString()
    }

    private fun setupToolbarView() {
        setSupportActionBar(detailActivityExamplesToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = squareRepository?.name ?: ""
    }

    private fun fetchBundleValus(savedInstanceState: Bundle?) {
        squareRepository = if (savedInstanceState != null) {
            savedInstanceState.getParcelable(ARG_BUNDLE_REPOSITORY)
        } else {
            intent.getParcelableExtra(ARG_BUNDLE_REPOSITORY)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_repository_detail, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuItemBookmark = menu?.findItem(R.id.action_bookmark)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_bookmark -> {
                // TODO Call the ViewModel here
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(ARG_BUNDLE_REPOSITORY, squareRepository)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val ARG_BUNDLE_REPOSITORY = "arg_bundle_repository"

        fun getStartIntent(context: Context, repository: SquareRepository): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(ARG_BUNDLE_REPOSITORY, repository)
            }
        }
    }
}