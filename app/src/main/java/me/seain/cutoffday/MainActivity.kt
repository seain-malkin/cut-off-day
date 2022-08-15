package me.seain.cutoffday

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.google.android.material.elevation.SurfaceColors
import me.seain.cutoffday.databinding.ActivityMainBinding
import me.seain.cutoffday.fragment.DayComponentFragment
import me.seain.cutoffday.fragment.MonthComponentFragment
import me.seain.cutoffday.fragment.StatusFragment
import me.seain.cutoffday.fragment.YearComponentFragment
import me.seain.cutoffday.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)

        // Apply surface colors to required UI elements
        binding.root.setBackgroundColor(SurfaceColors.SURFACE_0.getColor(this))
        binding.verifiedLayout?.setBackgroundColor(SurfaceColors.SURFACE_1.getColor(this))
        binding.bottomAppBar.setBackgroundColor(SurfaceColors.SURFACE_3.getColor(this))
        SurfaceColors.SURFACE_2.getColor(this).let {
            binding.borderView1.setBackgroundColor(it)
            binding.borderView2.setBackgroundColor(it)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().run {
                add(binding.dateTopFragment.id, YearComponentFragment())
                add(binding.dateMiddleFragment.id, MonthComponentFragment())
                add(binding.dateBottomFragment.id, DayComponentFragment())
                add(binding.bottomFragment.id, StatusFragment())
                commit()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val preferences = this.getPreferences(Context.MODE_PRIVATE)

        // Update UI when age changes
        model.cutoffAge.observe(this) { age ->
            age?.let {
                with (preferences.edit()) {
                    putInt(getString(me.seain.cutoffday.R.string.saved_cut_off_age_key), age)
                    apply()
                }
            }
        }

        // Get default cut off age from res/values/integers
        val defaultAge = resources.getInteger(R.integer.saved_cut_off_age_default_key)
        // Retrieve the saved cut off age in preferences or apply default
        val savedAge = preferences.getInt(getString(R.string.saved_cut_off_age_key), defaultAge)
        // Apply saved age to the view model
        model.setCutoffAge(savedAge)
    }

    override fun onStop() {
        super.onStop()
        model.cutoffAge.removeObservers(this)
    }

    /**
     * @see [onOptionsItemSelected]
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_increase -> {
                model.changeCutoffAge(1)
                true
            }
            R.id.action_decrease -> {
                model.changeCutoffAge(-1)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * @see [onCreateOptionsMenu]
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_control, menu)
        return super.onCreateOptionsMenu(menu)
    }
}