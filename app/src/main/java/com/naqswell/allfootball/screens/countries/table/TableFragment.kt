package com.naqswell.allfootball.screens.countries.table

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.naqswell.allfootball.MyApplication
import com.naqswell.allfootball.databinding.FragmentTableBinding
import com.naqswell.allfootball.domain.entities.TournamentTable
import com.naqswell.allfootball.domain.enums.Countries
import com.naqswell.allfootball.screens.countries.CountriesViewModel
import com.naqswell.allfootball.screens.countries.table.adapters.TableHeaderAdapter
import com.naqswell.allfootball.screens.countries.table.adapters.TableAdapter

class TableFragment : Fragment() {
    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!
    private val args: TableFragmentArgs by navArgs()
    private lateinit var type: Countries

    private val sharedViewModel: CountriesViewModel by activityViewModels {
        CountriesViewModel.provideFactory(((requireActivity().application) as MyApplication).allSportsApi)
    }

    private lateinit var tableHeaderAdapter: TableHeaderAdapter
    private lateinit var tableAdapter: TableAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        type = args.countryType

        return binding.apply {

            tableAdapter = TableAdapter { }
            tableHeaderAdapter = TableHeaderAdapter()
            concatAdapter = ConcatAdapter(tableHeaderAdapter, tableAdapter)
            recyclerView = rvTable.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = concatAdapter
            }

            sharedViewModel.tablesList.observe(viewLifecycleOwner) { tableList ->
                for (tableModel in tableList) {
                    if (tableModel.type == type) {
                        loadImages(tableModel)
                        tableAdapter.submitList(tableModel.data)
                    }
                }
            }

            txtTableTitle.apply {
                val mSpannableString = SpannableString(type.value)
                mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)
                txtTableTitle.text = mSpannableString
            }
        }.root
    }

    private fun loadImages(tableModel: TournamentTable) {
        with(binding) {
            Glide.with(imgCountry.context)
                .load(tableModel.icon)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .into(binding.imgCountry)
        }
    }
}