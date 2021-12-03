package com.flat14.study.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.flat14.study.app.databinding.FragmentCityListBinding
import com.flat14.study.domain.model.CityWeather
import kotlinx.android.synthetic.main.fragment_city_list.*
import org.koin.androidx.navigation.koinNavGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CityListFragment() : Fragment() {

    private val model: CityListViewModel by koinNavGraphViewModel(R.id.nav_graph)

    private val citiesList = mutableListOf<CityWeather>()
    private var _binding: FragmentCityListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.cityWeatherList.observe(this, Observer {
            citiesList.clear()
            citiesList.addAll(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityListRecyclerView.adapter = CityListAdapter(citiesList)
        cityListRecyclerView.layoutManager = LinearLayoutManager(context)
        cityListRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}