package com.pascal.movieku.presentation.ui.homepage.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.movieku.R
import com.pascal.movieku.data.network.model.response.movie.details.Reviews
import com.pascal.movieku.databinding.ItemReviewsBinding
import com.pascal.movieku.utils.validation

class ReviewsAdapter(
    val data: List<Reviews?>?,
    val itemClick: OnClickListener
) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = data!!.size

    inner class ViewHolder(val binding: ItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Reviews) {

            with(binding) {
                tvAuthor.text = validation(item.author)
                tvContent.text = validation(item.content)

                if (item.authorDetails.avatarPath != null) {
                    val url = item.authorDetails.avatarPath.removePrefix("/")
                    Glide.with(root)
                        .load(url)
                        .apply(
                            RequestOptions()
                                .override(1024, 1024)
                                .placeholder(R.drawable.ic_person)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                        )
                        .into(ivProfile)

                }
                root.setOnClickListener {
                    itemClick.detail(item)
                }
            }

        }
    }

    interface OnClickListener {
        fun detail(item: Reviews)
    }
}