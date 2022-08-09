package me.seain.cutoffday.fragment

import me.seain.cutoffday.R
import java.text.SimpleDateFormat
import java.util.*

class YearComponentFragment : DateComponentFragment() {

    override fun getDateComponent(date: Date): String {
        val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
        return formatter.format(date)
    }

    override fun getDateComponentLabel(): String {
        return "Year"
    }

    override fun getDateComponentColor(): Int {
        return R.color.primary_70
    }
}