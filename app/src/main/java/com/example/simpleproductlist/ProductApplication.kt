package com.example.simpleproductlist

import android.app.Application
import com.example.simpleproductlist.Api.ProductService
import com.example.simpleproductlist.Api.RetrofitHelper
import com.example.simpleproductlist.Repository.ProductRepository

class ProductApplication : Application() {

    lateinit var productRepository: ProductRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val employeeService = RetrofitHelper.getInstance().create(ProductService::class.java)
        productRepository = ProductRepository(employeeService,applicationContext)
    }

}