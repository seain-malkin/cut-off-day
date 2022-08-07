package me.seain.cutoffday.fragment

import me.seain.cutoffday.R
import java.util.*

class DayComponentFragment : DateComponentFragment() {

    override fun getDateComponent(date: Date): Int {
        return 7
    }

    override fun getDateComponentLabel(): String {
        return "Day"
    }

    override fun getDateComponentColor(): Int {
        return R.color.primary_70
    }
}