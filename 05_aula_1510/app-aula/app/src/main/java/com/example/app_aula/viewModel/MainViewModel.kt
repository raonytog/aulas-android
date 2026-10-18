package com.example.aula_2909

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app_aula.repository.client.ClientRetrofit
import com.example.app_aula.repository.model.BlogPostEntity
import com.example.app_aula.repository.service.BlogPostService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val postContent = MutableLiveData<String>()

    fun getPostContent(): LiveData<String> {
        return postContent
    }

    fun requestPost(id: String) {
        val apiBlogService = ClientRetrofit.createService(BlogPostService::class.java)
        val blogPost: Call<BlogPostEntity> = apiBlogService.getSinglePost(id)

        blogPost.enqueue(object : Callback<BlogPostEntity> {
            override fun onResponse(call: Call<BlogPostEntity>, response: Response<BlogPostEntity>) {
                postContent.value = "${response.body()?.id} - ${response.body()?.body}"
            }

            override fun onFailure(call: Call<BlogPostEntity>, t: Throwable) {
                postContent.value = "Falha na requisição da API"
            }
        })

    }

}

