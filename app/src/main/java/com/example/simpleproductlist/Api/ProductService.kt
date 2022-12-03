package com.example.simpleproductlist.Api

import com.example.simpleproductlist.Model.ProductList
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProduct() : Response<ProductList>
}