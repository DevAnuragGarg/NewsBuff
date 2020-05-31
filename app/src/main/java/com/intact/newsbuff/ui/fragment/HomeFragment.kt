package com.intact.newsbuff.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.intact.newsbuff.R
import com.intact.newsbuff.adapter.NewsListAdapter
import com.intact.newsbuff.data.Repository
import com.intact.newsbuff.databinding.HomeFragmentBinding
import com.intact.newsbuff.db.NewsDatabase
import com.intact.newsbuff.pojo.NewsDTO
import com.intact.newsbuff.util.listeners.OnNewsItemClickListener
import com.intact.newsbuff.viewmodel.FavoriteNewsViewModelFactory
import com.intact.newsbuff.viewmodel.HomeViewModel

class HomeFragment : Fragment(), OnNewsItemClickListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    // Use the 'by viewModels()' Kotlin property delegate using fragment ktx
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
        val application = requireNotNull(this.activity).application
        val dataSource = NewsDatabase.getDatabase(application).getNewsDao()
        val viewModelFactory = FavoriteNewsViewModelFactory(Repository(dataSource), application)

        // Get a reference to the ViewModel associated with this fragment.
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        // set up adapter
        newsListAdapter = NewsListAdapter(requireContext(), this)

        // setup recycler view
        with(binding.newsListRecyclerView) {
            layoutManager =
                LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            setHasFixedSize(true)
            adapter = newsListAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // no need to do as we are using the extensions for fragment above
        //viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setObservers()
    }

    private fun setObservers() {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {

            it?.let {
                newsListAdapter.submitList(it)
                binding.progressBar.visibility = View.GONE
                binding.newsListRecyclerView.visibility = View.VISIBLE
            }
        })

//        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { errorData ->
//            Toast.makeText(requireContext(), errorData.message, Toast.LENGTH_SHORT).show()
//            binding.progressBar.visibility = View.GONE
//        })
    }

    override fun onNewsItemClick(newsDTO: NewsDTO, showMenuItem: Boolean) {
        val bundle = bundleOf("news" to newsDTO)
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_right
                popExit = R.anim.slide_out_left
            }
        }
        requireView().findNavController().navigate(R.id.action_details_Screen, bundle, options)
    }

    override fun onNewsFavoriteSelected(newsDTO: NewsDTO) {
        viewModel.setFavoriteNews(newsDTO)
    }
}