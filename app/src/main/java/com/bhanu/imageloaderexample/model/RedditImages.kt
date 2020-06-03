package com.bhanu.imageloaderexample.model


/**
 * Created by Bhanu Prakash Pasupula on 03,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
data class RedditImages(
    val data: Data
){
    data class Data(
        val children: ArrayList<Children>
    ){
        data class Children(
            val data: RedditImages.Data.Children.Data
        ){
            data class Data(
                val thumbnail:String
            )
        }
    }
}