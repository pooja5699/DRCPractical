package com.drcpractical

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.drcpractical.databinding.BestSellerItemBinding
import com.drcpractical.network.model.BestsellerList

class BestSellerAdapter() :
    RecyclerView.Adapter<BestSellerViewHolder>() {

    private var context: Context? = null
    private var titles = mutableListOf<BestsellerList>()

    fun setTitle(customerRoutes: List<BestsellerList>) {
        this.titles = customerRoutes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        this.context = parent.context
        val inflater = LayoutInflater.from(parent.context)

        val binding = BestSellerItemBinding.inflate(inflater, parent, false)
        return BestSellerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        Glide.with(context!!).load(titles[position].image)
            .into(holder.binding.ivBestSeller)

        holder.binding.tvPrice.text =
            titles[position].currencyCode?.plus(" ")?.plus(titles[position].price)
        holder.binding.tvName.text = titles[position].name
        holder.binding.tvBrand.text = titles[position].mgsBrand
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}

class BestSellerViewHolder(val binding: BestSellerItemBinding) :
    RecyclerView.ViewHolder(binding.root)