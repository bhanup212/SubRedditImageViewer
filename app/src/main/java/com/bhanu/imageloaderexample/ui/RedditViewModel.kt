package com.bhanu.imageloaderexample.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhanu.imageloaderexample.model.RedditImages
import com.bhanu.imageloaderexample.network.ApiClient
import kotlinx.coroutines.launch


/**
 * Created by Bhanu Prakash Pasupula on 03,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
class RedditViewModel:ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    private val _redditImageList = MutableLiveData<RedditImages>()
    val redditImageList:LiveData<RedditImages> = _redditImageList

    fun fetchSubRedditImages(){

        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val res = ApiClient.apiClient.getSubRedditImages()
                _redditImageList.postValue(res)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _isLoading.postValue(false)
            }
        }
    }
}