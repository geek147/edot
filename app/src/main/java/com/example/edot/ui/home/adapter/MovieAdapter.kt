package com.example.edot.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.edot.R
import com.example.edot.databinding.ItemRowBinding
import com.example.edot.model.Movies.Movie

class MovieAdapter(private var fragment: Fragment) : RecyclerView.Adapter<MovieAdapter.MainViewHolder>() {
    private var listMovies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (listMovies.isEmpty()) {
            0
        } else {
            listMovies.size
        }
    }

    fun setData(list: List<Movie>) {
        this.listMovies.clear()
        this.listMovies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        listMovies[holder.bindingAdapterPosition].let {
            holder.bindData(it, fragment)
        }
    }

    class MainViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: Movie, fragment: Fragment) {
            Glide
                .with(fragment)
                .load(model.poster)
                .centerCrop()
                .placeholder(R.drawable.ic_not_found)
                .into(binding.imageView);

            binding.textViewTitle.text = model.title

        }
    }
}
