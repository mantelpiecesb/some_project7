package com.mantelpiecesb.weatherclient.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mantelpiecesb.weatherclient.databinding.ItemDailyBinding
import com.mantelpiecesb.weatherclient.model.Daily
import com.mantelpiecesb.weatherclient.utils.DataBeautifiers.getSimpleDateFormatRU
import com.mantelpiecesb.weatherclient.utils.DataBeautifiers.getTemperatureText


class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>()  {

   inner class WeatherViewHolder(private val binding: ItemDailyBinding): RecyclerView.ViewHolder(binding.root) {
       fun bind(daily: Daily) {
            binding.apply {
                binding.dayName.text = getSimpleDateFormatRU(daily.dt, "EE")
                binding.dayOfMonth.text = getSimpleDateFormatRU(daily.dt,"dd").trimStart('0')
                binding.dayTemperature.text = getTemperatureText(daily.temp.day)
            }
        }
   }

    private val callback = object : DiffUtil.ItemCallback<Daily>() {
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            ItemDailyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val dayTemp = differ.currentList[position]
        if (dayTemp != null) {
            holder.bind(dayTemp)
        }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}



