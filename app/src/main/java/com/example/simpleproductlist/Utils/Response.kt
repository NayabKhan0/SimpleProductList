package com.example.simpleproductlist.Utils

import com.example.simpleproductlist.Model.ProductList

sealed class Response<T>(val data: T? = null, val errorMsg: String? = null) {

    class Loading<T> : Response<T>()
    class Success<T>(productList: T? = null) : Response<T>(data = productList)
    class Error<T>(errorMsg: String) : Response<T>(errorMsg = errorMsg)
}