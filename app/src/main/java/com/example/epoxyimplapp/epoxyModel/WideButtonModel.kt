package com.example.epoxyimplapp.epoxyModel

import android.util.Log
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.epoxyimplapp.R
import com.example.epoxyimplapp.controller.onProductCliclListener
import com.example.epoxyimplapp.databinding.ComponentWideButtonBinding

@EpoxyModelClass(layout = R.layout.component_wide_button)
abstract class WideButtonModel : EpoxyModelWithHolder<WideButtonModel.WideButtonHolder>() {

    @field:EpoxyAttribute
    open var text: CharSequence? = null

    @field:EpoxyAttribute
    open var itemId: String? = null

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var listener: onProductCliclListener? = null

    override fun bind(holder: WideButtonHolder) {
        holder.binding.root.text = text
        holder.binding.root.setOnClickListener {
            itemId?.let { it1 -> listener?.onClick(it1) }
        }
    }

    class WideButtonHolder : EpoxyHolder() {
        lateinit var binding: ComponentWideButtonBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentWideButtonBinding.bind(itemView)
        }
    }
}