package com.example.epoxyimplapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.epoxyimplapp.Failed
import com.example.epoxyimplapp.ProductCategory
import com.example.epoxyimplapp.Success
import com.example.epoxyimplapp.controller.MenuController
import com.example.epoxyimplapp.controller.onProductCliclListener
import com.example.epoxyimplapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), onProductCliclListener {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private val controller: MenuController by lazy { MenuController(this) }
    private var buttonStatusMap: HashMap<String, Boolean> = HashMap()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.epoxyRecyclerView.setControllerAndBuildModels(controller)
        observeSignUpApiData()
        viewModel.getCategoriesData()
    }


    private fun observeSignUpApiData() {
        viewModel.categories.observe(this, Observer {
            when (it) {
                is Success -> {
                    val featureProducts = it.data as? List<ProductCategory>
                    if (featureProducts != null) {
                        controller.submit(featureProducts, buttonStatusMap)
                    }
                }
                is Failed -> {

                }
            }
        })
    }

    override fun onClick(id: String) {
        buttonStatusMap[id] = buttonStatusMap[id] != true
        controller.submitButtonStatusMap(buttonStatusMap)
    }

}