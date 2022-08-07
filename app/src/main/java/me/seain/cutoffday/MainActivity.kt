package me.seain.cutoffday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
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

        supportFragmentManager.beginTransaction().run {
            replace(binding.dateTopFragment.id, YearComponentFragment())
            replace(binding.dateMiddleFragment.id, MonthComponentFragment())
            replace(binding.dateBottomFragment.id, DayComponentFragment())
            replace(binding.bottomFragment.id, StatusFragment())
            commit()
        }
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