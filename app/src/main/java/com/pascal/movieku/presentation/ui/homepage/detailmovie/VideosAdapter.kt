package com.pascal.movieku.presentation.ui.homepage.detailmovie

import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pascal.movieku.R
import com.pascal.movieku.data.network.model.response.movie.details.DetailVideosResponse
import com.pascal.movieku.databinding.ItemVideosBinding
import com.pascal.movieku.utils.Constant.BASE_YT_IMG_URL

class VideosAdapter(
    val data: List<DetailVideosResponse.Videos?>?,
    val itemClick: OnClickListener
) : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount(): Int = data!!.size

    inner class ViewHolder(val binding: ItemVideosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailVideosResponse.Videos) {

            val image = "$BASE_YT_IMG_URL${item.key}/hqdefault.jpg"

            with(binding) {
                Glide.with(root)
                    .load(image)
                    .apply(
                        RequestOptions()
                            .override(1024, 1024)
                            .placeholder(R.color.black)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    )
                    .into(ivYt)

                root.setOnClickListener {
                    itemClick.detail(item)
                }
            }

        }
    }

    interface OnClickListener {
        fun detail(item: DetailVideosResponse.Videos)
    }
}