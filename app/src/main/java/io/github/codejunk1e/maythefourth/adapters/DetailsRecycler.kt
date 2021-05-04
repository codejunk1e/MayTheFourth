package io.github.codejunk1e.maythefourth.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.codejunk1e.maythefourth.databinding.DetailBinding

class DetailsAdapter(private val data: List<DetailModel>): RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: DetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailModel: DetailModel) {
            binding.itemTitle.text = detailModel.title
            binding.iteDescription.text = detailModel.detail
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])
    override fun getItemCount(): Int = data.size


    data class DetailModel(val title: String, val detail: String)
}