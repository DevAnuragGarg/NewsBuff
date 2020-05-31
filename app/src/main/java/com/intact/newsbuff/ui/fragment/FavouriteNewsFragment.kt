package com.intact.newsbuff.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.intact.newsbuff.R
import com.intact.newsbuff.adapter.FavoriteNewsListAdapter
import com.intact.newsbuff.data.Repository
import com.intact.newsbuff.db.NewsDatabase
import com.intact.newsbuff.viewmodel.FavoriteNewsViewModel
import com.intact.newsbuff.viewmodel.FavoriteNewsViewModelFactory
import timber.log.Timber

class FavouriteNewsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite_news, container, false)
        initializeVariables(view)
        return view
    }

    private fun initializeVariables(view: View) {
        val application = requireNotNull(this.activity).application
        val dataSource = NewsDatabase.getDatabase(application).getNewsDao()
        val viewModelFactory = FavoriteNewsViewModelFactory(Repository(dataSource), application)

        // Get a reference to the ViewModel associated with this fragment.
        val favoriteNewsViewModel =
            ViewModelProvider(this, viewModelFactory).get(FavoriteNewsViewModel::class.java)

        val adapter = FavoriteNewsListAdapter(requireContext())

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        favoriteNewsViewModel.newsDTO.observe(viewLifecycleOwner, Observer {
            it?.let {
                Timber.d("Anurag: ${it.toString()}")
                adapter.setData(it)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteNewsFragment()
    }
}
