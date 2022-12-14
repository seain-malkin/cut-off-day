package me.seain.cutoffday.fragment

import java.text.SimpleDateFormat
import java.util.*

class DayComponentFragment : DateComponentFragment() {

    override fun getDateComponent(date: Date): String {
        val formatter = SimpleDateFormat("dd", Locale.getDefault())
        return formatter.format(date)
    }

    override fun getDateComponentLabel(): String {
        return "Day"
    }
}