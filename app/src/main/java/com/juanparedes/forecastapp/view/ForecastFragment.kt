package com.juanparedes.forecastapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanparedes.forecastapp.R
import com.juanparedes.forecastapp.convertLocalTimeToPrettyFormat
import com.juanparedes.forecastapp.databinding.FragmentForecastBinding
import com.juanparedes.forecastapp.di.ComponentProvider
import com.juanparedes.forecastapp.domain.model.FetchForecastState
import com.juanparedes.forecastapp.domain.model.Forecast
import com.juanparedes.forecastapp.loadImage
import com.juanparedes.forecastapp.view.adapter.ForecastDaysAdapter
import javax.inject.Inject

class ForecastFragment : Fragment() {

    @Inject
    lateinit var factory: ForecastViewModelFactory
    private val viewModel: ForecastViewModel by viewModels { factory }

    private lateinit var binding: FragmentForecastBinding
    private lateinit var possibleLocationsAdapter: ArrayAdapter<String> //PossibleLocationsAdapter
    private val forecastDaysAdapter: ForecastDaysAdapter = ForecastDaysAdapter()

    private val onLocationClickedListener = OnItemClickListener { parent, view, position, id ->
        viewModel.fetchForecastByLocation((parent.getItemAtPosition(position) as String))
    }

    private val textChangedListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            viewModel.findPossibleLocations(s.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.run {
            (this.application as ComponentProvider).getComponent()
                .inject(this@ForecastFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        possibleLocationsAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line)

        binding.rvDays.apply {
            adapter = forecastDaysAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.searchBar.apply {
            onItemClickListener = onLocationClickedListener
            addTextChangedListener(textChangedListener)
            setAdapter(possibleLocationsAdapter)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getPossibleLocationsLivedata().observe(viewLifecycleOwner) {
            //possibleLocationsAdapter.setData(it.map { "${it.name}, ${it.country}" })
            possibleLocationsAdapter.addAll(it.map { "${it.name}, ${it.country}" })
            possibleLocationsAdapter.notifyDataSetChanged()
        }

        viewModel.getForecastByLocation().observe(viewLifecycleOwner) {
            selectState(it)
        }
    }

    private fun selectState(state: FetchForecastState) {
        when (state) {
            is FetchForecastState.EmptyState -> onEmptyState()
            is FetchForecastState.ErrorState -> onErrorState()
            is FetchForecastState.InitialState -> onInitialState()
            is FetchForecastState.LoadingState -> onLoadingState()
            is FetchForecastState.SearchResults -> {
                bindForecast(state.forecast)
                forecastDaysAdapter.submitList(state.forecast.forecastDays)
                onSearchResults()
            }
        }
    }

    private fun bindForecast(forecast: Forecast) {
        binding.layoutForecast.apply {
            icon.loadImage(forecast.currentWeather.weatherCondition.icon)
            tvTemp.text = getString(R.string.temperature_symbol,
                forecast.currentWeather.temperatureInCelsius.toString())
            tvNameAndCountry.text = getString(R.string.name_and_country,
                forecast.forecastLocation.name, forecast.forecastLocation.country)
            tvLocaltime.text = forecast.forecastLocation.localTime.convertLocalTimeToPrettyFormat()
        }
    }

    private fun onInitialState() {
        with(binding) {
            layoutForecast.root.isVisible = false
            tvEmptyResults.isVisible = true
            tvEmptyResults.text = getString(R.string.initial_state)
            progressBar.isVisible = false
            rvDays.isVisible = false
        }
    }

    private fun onLoadingState() {
        with(binding) {
            layoutForecast.root.isVisible = false
            tvEmptyResults.isVisible = false
            progressBar.isVisible = true
            rvDays.isVisible = false
        }
    }

    private fun onEmptyState() {
        with(binding) {
            layoutForecast.root.isVisible = false
            tvEmptyResults.isVisible = true
            tvEmptyResults.text = getString(R.string.empty_results)
            progressBar.isVisible = false
            rvDays.isVisible = false
        }
    }

    private fun onErrorState() {
        with(binding) {
            layoutForecast.root.isVisible = false
            tvEmptyResults.isVisible = true
            tvEmptyResults.text = getString(R.string.error_state)
            progressBar.isVisible = false
            rvDays.isVisible = false
        }
    }

    private fun onSearchResults() {
        with(binding) {
            layoutForecast.root.isVisible = true
            tvEmptyResults.isVisible = false
            progressBar.isVisible = false
            rvDays.isVisible = true
        }
    }
}