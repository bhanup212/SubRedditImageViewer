package com.bhanu.imageloaderexample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhanu.greedy.ImageLoader
import com.bhanu.imageloaderexample.R
import kotlinx.android.synthetic.main.fragment_image_view.*


class ImageViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("url")?.let {
            ImageLoader.load(it,imageView)
        }

        back_arrow.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}