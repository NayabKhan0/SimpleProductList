package com.example.simpleproductlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleproductlist.Adapter.MainAdapter
import com.example.simpleproductlist.Model.ProductList
import com.example.simpleproductlist.Utils.Response
import com.example.simpleproductlist.ViewModel.MainViewModel
import com.example.simpleproductlist.ViewModel.MainViewModelFactory
import com.example.simpleproductlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        val repository = (application as ProductApplication).productRepository
        val adapter = MainAdapter(this)
        mainBinding.recyclerView.adapter = adapter
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.productList.observe(this, Observer {
            when(it) {
                is Response.Error -> {
                    Toast.makeText(this,"${it.errorMsg}", Toast.LENGTH_SHORT).show()
                }
                is Response.Loading -> {
                    mainBinding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    mainBinding.progressBar.visibility = View.GONE
                    mainBinding.recyclerView.visibility = View.VISIBLE
                    it.data.let {
                        adapter.setList(it)
                    }
                }
            }
        })
    }
}