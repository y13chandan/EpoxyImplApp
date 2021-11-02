package com.example.epoxyimplapp.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.example.epoxyimplapp.ProductCategory
import com.example.epoxyimplapp.epoxyModel.card
import com.example.epoxyimplapp.epoxyModel.largeOverline
import com.example.epoxyimplapp.epoxyModel.loader
import com.example.epoxyimplapp.epoxyModel.wideButton

interface onProductCliclListener {
    fun onClick(id: String)
}

class MenuController(var listener: onProductCliclListener) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private var _currentResult: List<ProductCategory>? = null
    var buttonStatusMap: HashMap<String, Boolean> = HashMap()

    override fun buildModels() {
        val result = _currentResult

        if (result.isNullOrEmpty()) {
            loader { id("loader") }
            return
        }

        buildProducts(result)
    }


    private fun buildProducts(menuList: List<ProductCategory>?) {
        if (menuList == null) return
        for (item in menuList) {

            if (item.children != null) {
                buildProducts(item.children)
            }

            if (item.products == null || item.products.isEmpty()) {
                continue
            }

            largeOverline {
                id(item.id!!)
                value(item.name)
            }

            for (product in item.products.take(3)) {
                card {
                    id(product.productNumber!!)
                    title(product.name)
                    subtitle(product.formCode)
                    imageUrl(product.imageUrlOrNull)
                }
            }

            if (item.products.size > 3) {
                var buttonText = "Show All"
                if(buttonStatusMap[item.id] == true) {
                    buttonText = "Hide All"
                    for (product in item.products.take(2)) {
                        card {
                            id(product.productNumber!!+10000)
                            title(product.name)
                            subtitle(product.formCode)
                            imageUrl(product.imageUrlOrNull)
                        }
                    }
                }
                wideButton {
                    id("show-more-${item.id}")
                    text(buttonText)
                    itemId(item.id)
                    listener(listener)
                }
            }
        }
    }

    fun submit(result: List<ProductCategory>, map: HashMap<String, Boolean>) {
        _currentResult = result
        buttonStatusMap = map
        requestModelBuild()
    }

    fun submitButtonStatusMap(map: HashMap<String, Boolean>) {
        buttonStatusMap = map
        requestModelBuild()
    }

}
