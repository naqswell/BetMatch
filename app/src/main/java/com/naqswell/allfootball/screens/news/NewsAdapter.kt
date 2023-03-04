package com.naqswell.allfootball.screens.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.naqswell.allfootball.databinding.RvNewsItemBinding
import com.naqswell.allfootball.domain.entities.NewsModel

class NewsAdapter(private val onClick: () -> Unit) :
    ListAdapter<NewsModel, NewsAdapter.NewsViewHolder>(NewsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RvNewsItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(itemBinding, onClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsPage = getItem(position)
        holder.bind(newsPage)
    }

    inner class NewsViewHolder(private val binding: RvNewsItemBinding, val onClick: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsPage: NewsModel) {
            with(binding) {
                newsPage.img?.let { loadImages(it) }
                txtDateNews.text = newsPage.date
                txtTitleNews.text = newsPage.tittle
                txtNews.text = newsPage.text
            }
        }

        private fun loadImages(imgUrl: String) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(imgUrl)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
//                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .into(imgNews)
            }
        }

    }

    object NewsDiffCallback : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(
            oldItem: NewsModel,
            newItem: NewsModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NewsModel,
            newItem: NewsModel
        ): Boolean {
            return oldItem.tittle == newItem.tittle
        }
    }
}