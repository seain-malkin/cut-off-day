package me.seain.cutoffday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class MainViewModel : ViewModel() {
    // The age of the person used to calculate the cut-off date
    private var _cutoffAge: MutableLiveData<Int?> = MutableLiveData(null)
    val cutoffAge: LiveData<Int?>
        get() = _cutoffAge

    // The cut-off date based on the cut-off age
    private var _cutoffDate: MutableLiveData<Date?> = MutableLiveData(null)
    val cutoffDate: LiveData<Date?>
        get() = _cutoffDate

    fun setCutoffAge(age: Int) {
        _cutoffAge.value = age
        _cutoffDate.value = calcCutoffDate(age)
    }

    fun changeCutoffAge(delta: Int) {
        var age = when (_cutoffAge.value == null) {
            true -> 18
            false -> _cutoffAge.value!!
        }
        age += delta

        if (age < AGE_MIN) {
            age = AGE_MIN
        }
        if (age > AGE_MAX) {
            age = AGE_MAX
        }

        _cutoffAge.value = age
        _cutoffDate.value = calcCutoffDate(age)
    }

    companion object {
        private const val AGE_MIN = 15
        private const val AGE_MAX = 25

        fun calcCutoffDate(age: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, age * -1)
            return calendar.time
        }
    }
}