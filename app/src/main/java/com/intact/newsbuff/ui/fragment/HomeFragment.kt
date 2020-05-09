package com.intact.newsbuff.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.intact.newsbuff.adapter.NewsListAdapter
import com.intact.newsbuff.databinding.HomeFragmentBinding
import com.intact.newsbuff.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private lateinit var newsListAdapter: NewsListAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        initializeVariables()
        return binding.root
    }

    private fun initializeVariables() {
        // set up adapter
        newsListAdapter = NewsListAdapter(requireContext())

        // setup recycler view
        with(binding.newsListRecyclerView) {
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = newsListAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setObservers()
        viewModel.getTrendingNews()
    }

    private fun setObservers() {
        viewModel.trendingNewsLiveData.observe(viewLifecycleOwner, Observer { newScore ->
            newsListAdapter.newsListData = newScore
            newsListAdapter.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
            binding.newsListRecyclerView.visibility = View.VISIBLE
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { errorData ->
            Toast.makeText(requireContext(), errorData.message, Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        })
    }
}
