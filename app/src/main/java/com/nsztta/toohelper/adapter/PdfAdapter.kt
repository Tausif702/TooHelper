package com.nsztta.toohelper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nsztta.toohelper.databinding.NewsSampleBinding
import com.nsztta.toohelper.model_classes.PdfModel
class PdfAdapter(private val listener:PdfClickListner) :
    ListAdapter<PdfModel, PdfAdapter.PdfViewHolder>(PdfModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PdfViewHolder {
        val binding =
            NewsSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PdfViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PdfViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PdfViewHolder(private val binding: NewsSampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onPdfClicked(getItem(adapterPosition))
            }
        }

        fun bind(pdfModel: PdfModel) {
            binding.itemName.text = pdfModel.pdfName
        }
    }

    private class PdfModelDiffCallback : DiffUtil.ItemCallback<PdfModel>() {
        override fun areItemsTheSame(oldItem: PdfModel, newItem: PdfModel) =
            oldItem.pdfUrl == newItem.pdfUrl

        override fun areContentsTheSame(oldItem: PdfModel, newItem: PdfModel) = oldItem == newItem
    }

    interface PdfClickListner{
        fun onPdfClicked(pdfModel: PdfModel)
    }
}
