package com.kimboo.examples.ui.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimboo.core.models.SquareRepository
import com.kimboo.examples.R
import kotlinx.android.synthetic.main.view_holder_example_item.view.*


class ExampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.viewHolderExampleTitle
    val descriptionTextView: TextView = view.viewHolderExampleDescription
    val bookmarkImageView: ImageView = view.viewHolderExampleBookmark
}

class SquareRepoAdapter(
    context: Context,
    var callback: Callback
) : RecyclerView.Adapter<ExampleViewHolder>() {

    interface Callback {
        fun onRepositoryClicked(repository: SquareRepository)
    }

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    val repositories = mutableListOf<SquareRepository>()
    val bookmarkedRepositories = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        return ExampleViewHolder(
            inflater.inflate(R.layout.view_holder_example_item, parent, false)
        )
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val repository = repositories[position]
        holder.bookmarkImageView.visibility = if (bookmarkedRepositories.contains(repository.id)) {
            View.VISIBLE
        } else {
            View.GONE
        }
        holder.titleTextView.text = repository.name
        holder.descriptionTextView.text = "Stars: ${repository.stars}"
        holder.itemView.setOnClickListener {
            callback.onRepositoryClicked(repositories[position])
        }
    }
}
