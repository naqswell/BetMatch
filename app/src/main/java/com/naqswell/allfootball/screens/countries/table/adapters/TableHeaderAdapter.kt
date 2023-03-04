package com.naqswell.allfootball.screens.countries.table.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.allfootball.databinding.RvTableItemBinding

class TableHeaderAdapter : RecyclerView.Adapter<TableHeaderAdapter.HeaderHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RvTableItemBinding.inflate(inflater, parent, false)
        return HeaderHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HeaderHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class HeaderHolder(private val binding: RvTableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            with(binding) {
            }
        }
    }

}