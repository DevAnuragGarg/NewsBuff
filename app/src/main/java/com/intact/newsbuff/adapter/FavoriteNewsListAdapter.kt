package com.intact.newsbuff.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intact.newsbuff.databinding.ItemNewsBinding
import com.intact.newsbuff.pojo.NewsDTO
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FavoriteNewsListAdapter(private val context: Context) :
    RecyclerView.Adapter<FavoriteNewsViewHolder>() {

    private var newsData = ArrayList<NewsDTO>()
    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsViewHolder {
        return FavoriteNewsViewHolder.from(parent)
    }

    override fun getItemCount() = newsData.size

    override fun onBindViewHolder(holder: FavoriteNewsViewHolder, position: Int) {
        with(holder.binding) {
            with(newsData[position]) {
                titleTV.text = title
            }
        }
    }

    fun setData(data: List<NewsDTO>) {
        newsData = ArrayList(data)
        notifyDataSetChanged()
    }
}

class FavoriteNewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): FavoriteNewsViewHolder {
            val itemNewsBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context))
            return FavoriteNewsViewHolder(itemNewsBinding)
        }
    }
}