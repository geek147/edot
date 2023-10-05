package com.example.edot.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edot.R
import com.example.edot.databinding.ItemCarouselBinding
import com.example.edot.model.Movies

class CarouselAdapter(private var fragment: Fragment) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    private var listMovies: MutableList<Movies.Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (listMovies.isEmpty()) {
            0
        } else {
            listMovies.size
        }
    }

    fun setData(list: List<Movies.Movie>) {
        this.listMovies.clear()
        this.listMovies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        listMovies[holder.bindingAdapterPosition].let {
            holder.bindData(it, fragment)
        }
    }

    class CarouselViewHolder(private val binding: ItemCarouselBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: Movies.Movie, fragment: Fragment) {
            Glide
                .with(fragment)
                .load(model.poster)
                .centerCrop()
                .placeholder(R.drawable.ic_not_found)
                .into(binding.imageView);
        }
    }
}
