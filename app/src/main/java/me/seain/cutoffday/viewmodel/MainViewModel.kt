package me.seain.cutoffday.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _cutoffAge: MutableLiveData<Int> = MutableLiveData(18)

    val cutoffAge: LiveData<Int>
        get() = _cutoffAge

    fun changeCutoffAge(delta: Int) {
        var age = _cutoffAge.value!!
        age += delta
        if (age < AGE_MIN) {
            age = AGE_MIN
        }
        if (age > AGE_MAX) {
            age = AGE_MAX
        }
        _cutoffAge.value = age
    }

    companion object {
        private const val AGE_MIN = 15
        private const val AGE_MAX = 25
    }
}