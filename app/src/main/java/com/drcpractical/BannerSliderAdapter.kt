package com.drcpractical

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drcpractical.databinding.BannerSliderItemBinding
import com.drcpractical.network.model.BannerSlider

class BannerSliderAdapter() :
    RecyclerView.Adapter<CustomerDashboardViewHolder>() {

    private var context: Context? = null
    private var titles = mutableListOf<BannerSlider>()

    fun setTitle(customerRoutes: List<BannerSlider>) {
        this.titles = customerRoutes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerDashboardViewHolder {
        this.context = parent.context
        val inflater = LayoutInflater.from(parent.context)

        val binding = BannerSliderItemBinding.inflate(inflater, parent, false)
        return CustomerDashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerDashboardViewHolder, position: Int) {
        Glide.with(context!!).load(titles[position].mobileImage)
            .into(holder.binding.ivBanner)
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}

class CustomerDashboardViewHolder(val binding: BannerSliderItemBinding) :
    RecyclerView.ViewHolder(binding.root)