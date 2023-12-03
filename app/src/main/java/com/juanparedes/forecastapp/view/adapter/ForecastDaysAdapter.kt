package com.juanparedes.forecastapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juanparedes.forecastapp.R
import com.juanparedes.forecastapp.convertLocalTimeToPrettyFormatShort
import com.juanparedes.forecastapp.databinding.ForecastItemViewBinding
import com.juanparedes.forecastapp.domain.model.ForecastDay
import com.juanparedes.forecastapp.loadImage

class ForecastDaysAdapter :
    ListAdapter<ForecastDay, ForecastDaysAdapter.ForecastDaysResultsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ForecastDaysResultsViewHolder {
        return ForecastDaysResultsViewHolder(
            ForecastItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastDaysResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ForecastDaysResultsViewHolder(
        private val binding: ForecastItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ForecastDay) {
            binding.apply {
                with(result.day) {
                    icon.loadImage(weatherCondition.icon)
                    tvWeatherCondition.text = weatherCondition.text
                    tvTemp.text =  root.context.getString(
                        R.string.temperature_symbol,
                        averageDayTemperature.toString())
                    tvDay.text = result.date.convertLocalTimeToPrettyFormatShort()
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ForecastDay>() {
        override fun areItemsTheSame(oldItem: ForecastDay, newItem: ForecastDay) =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(oldItem: ForecastDay, newItem: ForecastDay) =
            oldItem == newItem
    }
}