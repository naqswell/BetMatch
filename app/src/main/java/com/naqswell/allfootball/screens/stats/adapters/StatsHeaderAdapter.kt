package com.naqswell.allfootball.screens.stats.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.allfootball.databinding.RvStatsAttackItemBinding
import com.naqswell.allfootball.databinding.RvStatsDefenseItemBinding
import com.naqswell.allfootball.domain.entities.StatsModel

class StatsHeaderAdapter(private var type: StatsModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (type) {
            is StatsModel.Attack -> {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = RvStatsAttackItemBinding.inflate(inflater, parent, false)
                StatsHeaderAttackHolder(itemBinding)
            }
            is StatsModel.Defense -> {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = RvStatsDefenseItemBinding.inflate(inflater, parent, false)
                StatsHeaderDefenseHolder(itemBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StatsHeaderAdapter.StatsHeaderAttackHolder -> {
            }
            is StatsHeaderAdapter.StatsHeaderDefenseHolder -> {
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class StatsHeaderAttackHolder(private val binding: RvStatsAttackItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class StatsHeaderDefenseHolder(private val binding: RvStatsDefenseItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}