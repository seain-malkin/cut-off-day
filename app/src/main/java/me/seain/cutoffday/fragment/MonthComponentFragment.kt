package me.seain.cutoffday.fragment

import me.seain.cutoffday.R
import java.util.*

class MonthComponentFragment : DateComponentFragment() {

    override fun getDateComponent(date: Date): Int {
        return 8
    }

    override fun getDateComponentLabel(): String {
        return "Month"
    }

    override fun getDateComponentColor(): Int {
        return R.color.primary_50
    }
}