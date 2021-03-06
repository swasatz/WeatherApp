package com.tinashe.weather.ui.home.vh

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.tinashe.weather.R
import com.tinashe.weather.utils.inflateView
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by tinashe on 2018/03/22.
 */
class AttributionHolder constructor(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun inflate(parent: ViewGroup):
                AttributionHolder = AttributionHolder(inflateView(R.layout.item_attribution, parent, false))
    }

    fun bind() {
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://darksky.net")
            val context = itemView.context

            intent.resolveActivity(context.packageManager)?.let {
                context.startActivity(intent)
            }
        }
    }
}