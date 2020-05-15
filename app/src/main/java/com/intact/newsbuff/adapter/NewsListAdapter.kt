package com.intact.newsbuff.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intact.newsbuff.R
import com.intact.newsbuff.databinding.ItemNewsBinding
import com.intact.newsbuff.pojo.NewsDTO
import com.intact.newsbuff.util.listeners.OnNewsItemClickListener
import java.text.SimpleDateFormat
import java.util.*


/**
 * The PagedListAdapter handles page load events using a PagedList.Callback object.
 * As the user scrolls, the PagedListAdapter calls PagedList.loadAround() to provide
 * hints to the underlying PagedList as to which items it should fetch from the DataSource.
 */
class NewsListAdapter(private val context: Context, private val listener: OnNewsItemClickListener) :
    PagedListAdapter<NewsDTO, NewsListViewHolder>(DIFF_CALLBACK) {

    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder.from(context, parent)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                with(getItem(position)!!) {
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
            listener.onNewsItemClick(getItem(position)!!, false)
        }

        holder.binding.moreIV.setOnClickListener {
            val popup = PopupMenu(it.context, it)
            popup.menuInflater.inflate(R.menu.item_news_menu, popup.menu)
            popup.setOnMenuItemClickListener {
                Toast.makeText(context, "Help Clicked", Toast.LENGTH_SHORT).show()
                return@setOnMenuItemClickListener true
            }
            popup.show()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<NewsDTO>() {
            override fun areItemsTheSame(
                oldNews: NewsDTO,
                newNews: NewsDTO
            ) = oldNews.title == newNews.title

            override fun areContentsTheSame(
                oldNews: NewsDTO,
                newNews: NewsDTO
            ) = oldNews == newNews
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