package com.hieuwu.groceriesstore.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.hieuwu.groceriesstore.R
import com.hieuwu.groceriesstore.databinding.FragmentExploreBinding
import com.hieuwu.groceriesstore.presentation.adapters.GridListItemAdapter
import timber.log.Timber


class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentExploreBinding>(
            inflater, R.layout.fragment_explore, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dataSet: ArrayList<String> = ArrayList()
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")
        dataSet.add("a")
        dataSet.add("b")

        setUpRecyclerView(dataSet)
    }

    private fun setUpRecyclerView(dataSet: ArrayList<String>) {
        binding.productRecyclerview.adapter =
            GridListItemAdapter(GridListItemAdapter.OnClickListener {
                Timber.d("on item clicked")
            })
    }
}