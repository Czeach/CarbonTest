package com.czech.features.moviesList.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.czech.core.response.MovieList
import com.czech.core.utils.Constants.BASE_IMAGE_PATH
import com.czech.features.R

class MoviesListAdapter(diffCallback: MoviesListDiffCallback) :
    ListAdapter<MovieList.Result, MoviesListAdapter.MoviesListViewHolder>(diffCallback) {

    var onClickItemListener: ((MovieList.Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false)

        return MoviesListViewHolder(view)
    }

    inner class MoviesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var poster: ImageView = itemView.findViewById(R.id.poster_image)
        private var title: TextView = itemView.findViewById(R.id.title)
        private var date: TextView = itemView.findViewById(R.id.date)
        private var vote: TextView = itemView.findViewById(R.id.vote)

        fun bind(result: MovieList.Result) {
            date.text = result.releaseDate
            vote.text = result.voteAverage.toString()
            title.text = result.title
            Glide.with(itemView)
                .load("$BASE_IMAGE_PATH${result.posterPath}")
                .into(poster)
        }
    }

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onClickItemListener?.invoke(data)
        }
    }
}

object MoviesListDiffCallback : DiffUtil.ItemCallback<MovieList.Result>() {
    override fun areItemsTheSame(oldItem: MovieList.Result, newItem: MovieList.Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: MovieList.Result,
        newItem: MovieList.Result
    ): Boolean {
        return oldItem.id == newItem.id
    }
}