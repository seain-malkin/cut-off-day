package me.seain.cutoffday.fragment

import me.seain.cutoffday.R
import java.text.SimpleDateFormat
import java.util.*

class MonthComponentFragment : DateComponentFragment() {

    override fun getDateComponent(date: Date): String {
        val formatter = SimpleDateFormat("MM", Locale.getDefault())
        return formatter.format(date)
    }

    override fun getDateComponentLabel(): String {
        return "Month"
    }

    override fun getDateComponentColor(): Int {
        return R.color.primary_50
    }
}