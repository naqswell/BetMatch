package com.naqswell.allfootball.screens.stats.adapters

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.allfootball.R
import com.naqswell.allfootball.databinding.RvStatsAttackItemBinding
import com.naqswell.allfootball.databinding.RvStatsDefenseItemBinding
import com.naqswell.allfootball.domain.entities.StatsModel

class StatsAdapter(private var statsModelList: List<StatsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (val statsRow = statsModelList.firstOrNull()) {
            is StatsModel.Attack -> {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = RvStatsAttackItemBinding.inflate(inflater, parent, false)
                StatsAttackHolder(itemBinding)
            }
            is StatsModel.Defense -> {
                val inflater = LayoutInflater.from(parent.context)
                val itemBinding = RvStatsDefenseItemBinding.inflate(inflater, parent, false)
                StatsDefenseHolder(itemBinding)
            }
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val statsRow = statsModelList[position]
        when (holder) {
            is StatsAttackHolder -> {
                holder.bindAttack(position, statsRow as StatsModel.Attack)
            }
            is StatsDefenseHolder -> {
                holder.bindDefense(position, statsRow as StatsModel.Defense)
            }
        }
    }

    override fun getItemCount(): Int {
        return statsModelList.size
    }

    inner class StatsAttackHolder(private val binding: RvStatsAttackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var row: StatsModel.Attack? = null

        fun bindAttack(position: Int, statsModel: StatsModel.Attack) {
            row = statsModel
            colorizeRow(position)
            with(binding) {
                txtTeam.text = (row as StatsModel.Attack).team
                txtTournament.text = (row as StatsModel.Attack).tournament
                txtShots.text = (row as StatsModel.Attack).shots1.toString()
                txtDribbling.text = (row as StatsModel.Attack).dribbling.toString()
                txtRating.text = (row as StatsModel.Attack).rating.toString()
            }
        }

        private fun colorizeRow(position: Int) {
            with(binding) {
                if (position % 2 == 0) {
                    rvLinearRoot.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.rvLinearRoot.context,
                            R.color.blue
                        )
                    )
                } else {
                    val typedValue = TypedValue();
                    binding.root.context.theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true);
                    val color = ContextCompat.getColor(binding.root.context, typedValue.resourceId)
                    rvLinearRoot.setBackgroundColor(color)
                }
            }
        }
    }


    inner class StatsDefenseHolder(private val binding: RvStatsDefenseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var row: StatsModel.Defense? = null

        fun bindDefense(position: Int, row: StatsModel.Defense) {
            this.row = row
            colorizeRow(position)
            with(binding) {
                txtTeam.text = (row as StatsModel.Defense).team
                txtTournament.text = (row as StatsModel.Defense).tournament
                txtShots.text = (row as StatsModel.Defense).kicks.toString()
                txtTakes.text = (row as StatsModel.Defense).tackle.toString()
                txtRating.text = (row as StatsModel.Defense).rating.toString()
            }
        }

        private fun colorizeRow(position: Int) {
            with(binding) {
                if (position % 2 == 0) {
                    rvLinearRoot.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.rvLinearRoot.context,
                            R.color.blue
                        )
                    )
                } else {
                    val typedValue = TypedValue();
                    binding.root.context.theme.resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true);
                    val color = ContextCompat.getColor(binding.root.context, typedValue.resourceId)
                    rvLinearRoot.setBackgroundColor(color)
                }
            }
        }
    }
}