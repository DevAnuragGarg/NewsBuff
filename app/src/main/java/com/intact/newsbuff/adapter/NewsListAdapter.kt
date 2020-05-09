package com.intact.newsbuff.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intact.newsbuff.databinding.ItemNewsBinding
import com.intact.newsbuff.pojo.NewsDTO
import com.intact.newsbuff.util.listeners.OnNewsItemClickListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class NewsListAdapter(private val context: Context, private val listener: OnNewsItemClickListener) :
    RecyclerView.Adapter<NewsListViewHolder>() {

    var newsListData = ArrayList<NewsDTO>()
    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder.from(context, parent)
    }

    override fun getItemCount(): Int {
        return newsListData.size
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                with(newsListData[position]) {
                    titleTV.text = title
                    descriptionTV.text = description
                    Glide.with(context).load(urlToImage).into(newsImageIV)
                    sourceNameTV.text = source.name
                    val date = format.parse(publishedAt)
                    publishedTimeTV.text = DateUtils.getRelativeTimeSpanString(
                        date!!.time,
                        Calendar.getInstance().timeInMillis,
                        DateUtils.MINUTE_IN_MILLIS
                    )
                }
            }
        }

        holder.binding.cardView.setOnClickListener {
            listener.onNewsItemClick(newsListData[position])
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