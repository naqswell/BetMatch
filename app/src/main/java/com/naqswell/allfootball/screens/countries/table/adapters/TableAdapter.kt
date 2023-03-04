package com.naqswell.allfootball.screens.countries.table.adapters

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.allfootball.R
import com.naqswell.allfootball.databinding.RvTableItemBinding
import com.naqswell.allfootball.domain.entities.TableDataModel


class TableAdapter(private val onClick: () -> Unit) :
    ListAdapter<TableDataModel, TableAdapter.TableRowViewHolder>(TableRowDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableRowViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RvTableItemBinding.inflate(inflater, parent, false)
        return TableRowViewHolder(itemBinding, onClick)
    }

    override fun onBindViewHolder(holder: TableRowViewHolder, position: Int) {
        val row = getItem(position)
        holder.bind(position, row)
    }

    inner class TableRowViewHolder(
        private val binding: RvTableItemBinding,
        val onClick: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, row: TableDataModel) {
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
                txtNumber.text = position.toString()
                txtTeam.text = row.team
                txtGames.text = row.games.toString()
                txtWons.text = row.wons.toString()
                txtDraws.text = row.draws.toString()
                txtLosses.text = row.losses.toString()
                txtGoals.text = row.goals.toString()
            }
        }
    }

    object TableRowDiffCallback : DiffUtil.ItemCallback<TableDataModel>() {
        override fun areItemsTheSame(
            oldItem: TableDataModel,
            newItem: TableDataModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TableDataModel,
            newItem: TableDataModel
        ): Boolean {
            return oldItem.team == newItem.team
        }
    }
}

