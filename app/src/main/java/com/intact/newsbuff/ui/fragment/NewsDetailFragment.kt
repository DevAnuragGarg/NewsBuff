package com.intact.newsbuff.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.intact.newsbuff.databinding.NewsDetailFragmentBinding
import com.intact.newsbuff.pojo.NewsDTO
import timber.log.Timber

class NewsDetailFragment : Fragment() {

    companion object {
        fun newInstance() = NewsDetailFragment()
    }

    private lateinit var newsDTO: NewsDTO
    private var _binding: NewsDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsDetailFragmentBinding.inflate(inflater, container, false)
        initializeVariables()
        return binding.root
    }

    private fun initializeVariables() {
        newsDTO = arguments?.getParcelable<NewsDTO>("news")!!
        Timber.d("News DTO received: $newsDTO")
        updateUI()
    }

    private fun updateUI() {
        with(binding) {
            with(newsDTO) {
                titleTV.text = description
                Glide.with(requireContext()).load(urlToImage).into(newsImageIV)
                descriptionTV.text = title
            }
        }
    }
}
