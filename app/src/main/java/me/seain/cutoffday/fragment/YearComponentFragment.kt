package me.seain.cutoffday.fragment

import me.seain.cutoffday.R
import java.util.*

class YearComponentFragment : DateComponentFragment() {

    override fun getDateComponent(date: Date): Int {
        return 2022
    }

    override fun getDateComponentLabel(): String {
        return "Year"
    }

    override fun getDateComponentColor(): Int {
        return R.color.primary_30
    }
}