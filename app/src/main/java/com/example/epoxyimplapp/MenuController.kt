package com.example.epoxyimplapp

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.example.epoxyimplapp.epoxyModel.card
import com.example.epoxyimplapp.epoxyModel.largeOverline
import com.example.epoxyimplapp.epoxyModel.loader
import com.example.epoxyimplapp.epoxyModel.wideButton

class MenuController : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private var _currentResult: List<ProductCategory>? = null

    override fun buildModels() {
        val result = _currentResult

        if (result.isNullOrEmpty()) {
            loader { id("loader") }
            return
        }

        buildProducts(result)
    }


    private fun buildProducts(menus: List<ProductCategory>?) {
        if (menus == null) return
        for (item in menus) {

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
                wideButton {
                    id("show-more-${item.id}")
                    text("Show All")
                }
            }
        }
    }

    fun submit(result: List<ProductCategory>) {
        _currentResult = result
        requestModelBuild()
    }

}
