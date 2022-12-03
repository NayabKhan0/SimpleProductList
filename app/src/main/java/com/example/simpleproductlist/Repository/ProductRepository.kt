package com.example.simpleproductlist.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simpleproductlist.Api.ProductService
import com.example.simpleproductlist.Model.ProductList
import com.example.simpleproductlist.Utils.NetworkUtils
import com.example.simpleproductlist.Utils.Response


class ProductRepository(
    private val productService: ProductService,
    private val applicationContext: Context
) {

    private val productLiveData = MutableLiveData<Response<ProductList>>()
    val product: LiveData<Response<ProductList>>
        get() = productLiveData

    suspend fun getProduct() {
        if (NetworkUtils.isOnline(applicationContext)) {
            try {
                val result = productService.getProduct()
                if (result.body() != null) {
                       productLiveData.postValue(Response.Success(result.body()))
                }
            } catch (e: Exception) {
                productLiveData.postValue(Response.Error(e.toString()))
            }
        } else {
            productLiveData.postValue(Response.Error("Please Check Internet Connection"))
        }
    }
}