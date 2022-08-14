package me.seain.cutoffday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

/**
 * A Simple [ViewModel] used by the main activity and its fragments.
 */
class MainViewModel : ViewModel() {
    // The age of the person used to calculate the cut-off date
    private var _cutoffAge: MutableLiveData<Int?> = MutableLiveData(null)
    val cutoffAge: LiveData<Int?>
        get() = _cutoffAge

    // The cut-off date based on the cut-off age
    private var _cutoffDate: MutableLiveData<Date?> = MutableLiveData(null)
    val cutoffDate: LiveData<Date?>
        get() = _cutoffDate

    /**
     * Set the cut off age value. The value will be limited to an allowed range.
     *
     * @param age New cut-off age.
     */
    fun setCutoffAge(age: Int) {
        age.coerceIn(AGE_MIN, AGE_MAX).let {
            _cutoffAge.value = it
            _cutoffDate.value = calcCutoffDate(it)
        }
    }

    /**
     * Change the cut-off age by a certain amount.
     *
     * @param delta The amount of change to the age.
     */
    fun changeCutoffAge(delta: Int) {
        // Apply the default age if age isn't set
        var age = when (_cutoffAge.value == null) {
            true -> AGE_DEFAULT
            false -> _cutoffAge.value!!
        }

        age += delta
        setCutoffAge(age)
    }

    companion object {
        private const val AGE_MIN = 15
        private const val AGE_MAX = 25
        private const val AGE_DEFAULT = 18

        /**
         * Calculates the maximum date someone could be born to be at least the age set as the
         * cut-off.
         *
         * @param age The age in years to subtract from the current date.
         * @return The relative date given the age.
         */
        fun calcCutoffDate(age: Int): Date {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, age * -1)
            return calendar.time
        }
    }
}