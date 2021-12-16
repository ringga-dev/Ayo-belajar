package com.ringga.appfood.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ringga.appfood.R
import com.ringga.appfood.databinding.ItemPagerLoadingBinding

class PagerLoadingAdapter(private val context: Context, private val words: List<String>) :
    RecyclerView.Adapter<PagerLoadingAdapter.PageHolder>() {


    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPagerLoadingBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.item_pager_loading, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        with(holder) {
            // TODO
            binding.textView.text = words[position]
        }
    }

    override fun getItemCount(): Int = words.size

}

