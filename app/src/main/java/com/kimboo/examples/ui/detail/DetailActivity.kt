package com.kimboo.examples.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimboo.core.models.SquareRepository
import com.kimboo.examples.R
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_activity_info_section.*

/**
 * Displays the Repository details. There's not much to do here since we are not fetching anything
 * from any API. That's why this Activity doesn't have a ViewModel.
 */
class DetailActivity : AppCompatActivity() {

    private var squareRepository: SquareRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
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