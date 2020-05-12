package com.intact.newsbuff.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.intact.newsbuff.R

class FavouriteNewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_news, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteNewsFragment()
    }
}
