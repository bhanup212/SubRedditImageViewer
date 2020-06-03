package com.bhanu.imageloaderexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhanu.greedy.ImageLoader
import com.bhanu.imageloaderexample.R
import com.bhanu.imageloaderexample.model.RedditImages
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_row_layout.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RedditViewModel
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this).get(RedditViewModel::class.java)
        observeViewModel()
        viewModel.fetchSubRedditImages()
    }
    private fun observeViewModel(){
        viewModel.isLoading.observe(this, Observer {
            progressbar.isVisible = it
        })
        viewModel.redditImageList.observe(this, Observer {
            adapter.setList(it.data.children)
        })
    }
    private fun setUpRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        adapter = ImageAdapter()
        recyclerView.adapter = adapter
    }
    private fun navigateToFullScreenFragment(url:String){
        val fragment = ImageViewFragment()
        fragment.arguments = Bundle().apply {
            putString("url",url)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .addToBackStack(null)
            .commit()
    }

    inner class ImageAdapter:RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

        private val redditImages = ArrayList<RedditImages.Data.Children>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(applicationContext).inflate(R.layout.image_row_layout,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return redditImages.size
        }
        fun setList(list:ArrayList<RedditImages.Data.Children>){
            redditImages.clear()
            redditImages.addAll(list)
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val url = redditImages[position].data.thumbnail
            ImageLoader.load(url,holder.itemView.imageView)

            holder.itemView.rootLayout.setOnClickListener {
                navigateToFullScreenFragment(url)
            }
        }

        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view)
    }
}