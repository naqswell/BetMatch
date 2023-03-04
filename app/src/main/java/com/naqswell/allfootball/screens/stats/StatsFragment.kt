package com.naqswell.allfootball.screens.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.R
import com.naqswell.allfootball.databinding.FragmentStatsBinding
import com.naqswell.allfootball.domain.entities.StatsModel
import com.naqswell.allfootball.screens.stats.adapters.StatsAdapter
import com.naqswell.allfootball.screens.stats.adapters.StatsHeaderAdapter

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    private val statsViewModel: StatsViewModel by viewModels {
        StatsViewModel.provideFactory(((requireActivity().application) as MyApplication).allSportsApi)
    }

    private lateinit var statsHeaderAdapter: StatsHeaderAdapter
    private lateinit var statsAdapter: StatsAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)

        return binding.apply {
            bindToggleGroup()

            statsViewModel.attackStatsListModel.observe(viewLifecycleOwner) { tableList ->
                statsHeaderAdapter = StatsHeaderAdapter(StatsModel.Attack())
                statsAdapter = StatsAdapter(tableList)
                concatAdapter = ConcatAdapter(statsHeaderAdapter, statsAdapter)
                recyclerView = rvStats.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = concatAdapter
                }
            }

            statsViewModel.defenseStatsListModel.observe(viewLifecycleOwner) { tableList ->
                statsHeaderAdapter = StatsHeaderAdapter(StatsModel.Defense())
                statsAdapter = StatsAdapter(tableList)
                concatAdapter = ConcatAdapter(statsHeaderAdapter, statsAdapter)
                recyclerView = rvStats.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = concatAdapter
                }
            }

        }.root
    }

    private fun bindToggleGroup() {
        with(binding) {
            toggleGroupStats.addOnButtonCheckedListener { toggleGroupStats, checkedId, isChecked ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.btn_attack -> {
                            btnAttack.setBackgroundColor(
                                ContextCompat.getColor(
                                    btnAttack.context,
                                    R.color.blue
                                )
                            )
                            btnDefense.setBackgroundColor(
                                ContextCompat.getColor(
                                    btnAttack.context,
                                    R.color.orange
                                )
                            )
                            statsViewModel.fetchAttackStats()
                        }
                        R.id.btn_defense -> {
                            btnDefense.setBackgroundColor(
                                ContextCompat.getColor(
                                    btnAttack.context,
                                    R.color.blue
                                )
                            )
                            btnAttack.setBackgroundColor(
                                ContextCompat.getColor(
                                    btnAttack.context,
                                    R.color.orange
                                )
                            )
                            statsViewModel.fetchDefenseStats()
                        }
                    }
                }
            }
            toggleGroupStats.check(R.id.btn_defense)
        }
    }
}