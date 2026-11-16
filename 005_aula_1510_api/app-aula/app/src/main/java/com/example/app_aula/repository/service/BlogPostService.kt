package com.example.app_aula.repository.service

import com.example.app_aula.repository.model.BlogPostEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BlogPostService {

    @GET("posts")
    fun getAllBlogPosts(): Call<List<BlogPostEntity>>

    @GET("posts/{postId}")
    fun getSinglePost(@Path("postId")postId: String): Call<BlogPostEntity>

    @POST("posts")
    fun setSinglePost(@Body blogPost: BlogPostEntity): Call<BlogPostEntity>

    @FormUrlEncoded
    @POST("posts")
    fun setSinglePost(@Field("userID") userID: String): Call<BlogPostEntity>

}