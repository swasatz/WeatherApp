package com.tinashe.weather.ui.home.vh

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.tinashe.weather.R
import com.tinashe.weather.model.DateFormat
import com.tinashe.weather.model.Entry
import com.tinashe.weather.model.WeatherData
import com.tinashe.weather.utils.DateUtil
import com.tinashe.weather.utils.glide.GlideApp
import com.tinashe.weather.utils.inflateView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.weather_curr_day_item.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by tinashe on 2018/03/21.
 */
class CurrentDayHolder constructor(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var adapter: HoursAdapter

    companion object {
        fun inflate(parent: ViewGroup):
                CurrentDayHolder = CurrentDayHolder(inflateView(R.layout.weather_curr_day_item, parent, false))
    }

    fun bind(current: Entry, hourly: WeatherData) {
        //dayBackground.setBackgroundResource(WeatherUtil.getWeatherTheme(current.icon))
        val context = itemView.context

        //TODO: Add more backgrounds
        GlideApp.with(context)
                .load(R.drawable.bg_preview)
                .into(dayBackgroundImg)


        val date = Date(TimeUnit.MILLISECONDS.convert(current.time, TimeUnit.SECONDS))
        currentTime.text = DateUtil.getFormattedDate(date, DateFormat.TIME)
        currentTemperature.text = context.getString(R.string.degrees, current.temperature.toInt())
        currentSummary.text = current.summary

        listView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = HoursAdapter()
        listView.adapter = adapter
        adapter.entries = hourly.data.toMutableList()
    }

    class HoursAdapter : RecyclerView.Adapter<HourHolder>() {

        var entries = mutableListOf<Entry>()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourHolder = HourHolder.inflate(parent)

        override fun getItemCount(): Int = entries.size

        override fun onBindViewHolder(holder: HourHolder, position: Int) {
            holder.bind(entries[position])
        }

    }
}