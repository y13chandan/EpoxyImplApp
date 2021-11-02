package com.example.epoxyimplapp.epoxyModel

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.epoxyimplapp.R
import com.example.epoxyimplapp.databinding.ComponentLoaderBinding

@EpoxyModelClass(layout = R.layout.component_loader)
abstract class LoaderModel : EpoxyModelWithHolder<LoaderModel.LoaderModelHolder>() {

    override fun bind(holder: LoaderModelHolder) {
    }

    class LoaderModelHolder : EpoxyHolder() {
        lateinit var binding: ComponentLoaderBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentLoaderBinding.bind(itemView)
        }
    }
}