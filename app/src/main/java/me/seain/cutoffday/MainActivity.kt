package me.seain.cutoffday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.elevation.SurfaceColors
import me.seain.cutoffday.databinding.ActivityMainBinding
import me.seain.cutoffday.fragment.StatusFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var statusFragment: StatusFragment

    private var age = 18

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)

        // Change app bar surface color
        val colorSurface3 = SurfaceColors.SURFACE_3.getColor(this)
        binding.bottomAppBar.setBackgroundColor(colorSurface3)

        supportFragmentManager.beginTransaction().run {
            statusFragment = StatusFragment()
            replace(binding.bottomFragment.id, statusFragment)
            commit()
        }
    }

    private fun onAgeChange(delta: Int) {
        age += delta
        if (age < AGE_MIN) {
            age = AGE_MIN
        }
        if (age > AGE_MAX) {
            age = AGE_MAX
        }
        statusFragment.updateAge(age)
    }

    /**
     * @see [onOptionsItemSelected]
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_increase -> {
                onAgeChange(1)
                true
            }
            R.id.action_decrease -> {
                onAgeChange(-1)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_control, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        private const val AGE_MIN = 15
        private const val AGE_MAX = 25
    }
}