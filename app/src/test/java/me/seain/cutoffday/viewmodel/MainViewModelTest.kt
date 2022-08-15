package me.seain.cutoffday.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var model: MainViewModel

    @Before
    fun setup() {
        model = MainViewModel()
    }

    @Test
    fun setCutoffAge() {
        model.setCutoffAge(18)

        assertEquals(18, model.cutoffAge.value)
    }

    @Test
    fun changeCutoffAge_decrease_within_range() {
        model.setCutoffAge(18)
        model.changeCutoffAge(-1)

        assertEquals(17, model.cutoffAge.value)
    }

    @Test
    fun changeCutoffAge_increase_within_range() {
        model.setCutoffAge(18)
        model.changeCutoffAge(+1)

        assertEquals(19, model.cutoffAge.value)
    }
}