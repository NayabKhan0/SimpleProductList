package com.example.simpleproductlist.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleproductlist.Model.ProductList
import com.example.simpleproductlist.Repository.ProductRepository
import com.example.simpleproductlist.Utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val productRepository: ProductRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.getProduct()
        }
    }

    val productList :LiveData<Response<ProductList>>
        get() = productRepository.product

}