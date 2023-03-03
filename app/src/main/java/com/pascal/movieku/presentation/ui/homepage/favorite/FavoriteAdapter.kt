package com.pascal.movieku.presentation.ui.homepage.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pascal.movieku.data.local.model.entity.FavoriteEntity
import com.pascal.movieku.databinding.ItemCardFavoriteBinding
import com.pascal.movieku.utils.Constant
import com.bumptech.glide.Glide

class FavoriteAdapter(private val onClick: (FavoriteEntity) -> Unit) :
    ListAdapter<FavoriteEntity, FavoriteAdapter.FavoriteHolder>(
        Differ()
    ) {
    class FavoriteHolder(
        private val binding: ItemCardFavoriteBinding,
        private val onClick: (FavoriteEntity) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteEntity: FavoriteEntity) {
            binding.apply {
                Glide.with(root)
                    .load("${Constant.BASE_IMAGE}${favoriteEntity.poster}")
                    .centerCrop()
                    .into(ivPoster)
                tvTitle.text = favoriteEntity.title
                tvOverview.text = favoriteEntity.overview
                tvRating.text = favoriteEntity.rating.toString()
                root.setOnClickListener {
                    onClick(favoriteEntity)
                }
            }
        }
    }

    class Differ: DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.idMovie == newItem.idMovie
        }
        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding = ItemCardFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(getItem(position))
    }
}