package me.seain.cutoffday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.elevation.SurfaceColors
import me.seain.cutoffday.databinding.ActivityMainBinding
import me.seain.cutoffday.fragment.StatusFragment
import me.seain.cutoffday.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var statusFragment: StatusFragment
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)

        val colorSurface1 = SurfaceColors.SURFACE_1.getColor(this)
        binding.verifiedLayout.setBackgroundColor(colorSurface1)

        statusFragment = StatusFragment()

        supportFragmentManager.beginTransaction().run {
            replace(binding.bottomFragment.id, statusFragment)
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