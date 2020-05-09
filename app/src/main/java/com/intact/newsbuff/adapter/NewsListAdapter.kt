package com.intact.newsbuff.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intact.newsbuff.databinding.ItemNewsBinding
import com.intact.newsbuff.pojo.NewsDTO

class NewsListAdapter(private val context: Context) : RecyclerView.Adapter<NewsListViewHolder>() {

    var newsListData = ArrayList<NewsDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder.from(context, parent)
    }

    override fun getItemCount(): Int {
        return newsListData.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                titleTV.text = newsListData[position].title
            }
        }
    }
}

class NewsListViewHolder private constructor(val binding: ItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(context: Context, parent: ViewGroup): NewsListViewHolder {
            val view = ItemNewsBinding.inflate(LayoutInflater.from(context), parent, false)
            return NewsListViewHolder(view)
        }
    }
}