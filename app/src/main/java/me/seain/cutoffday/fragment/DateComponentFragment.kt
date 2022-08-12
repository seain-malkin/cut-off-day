package me.seain.cutoffday.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.elevation.SurfaceColors
import me.seain.cutoffday.databinding.FragmentDateComponentBinding
import me.seain.cutoffday.viewmodel.MainViewModel
import java.util.*

abstract class DateComponentFragment : Fragment() {

    private val model: MainViewModel by activityViewModels()

    // Template methods
    /**
     * Returns the value of the date component such as the year, month, day.
     *
     * @param date The cut-off date
     * @return Value of the date component, e.g. 2022; 7; 31
     */
    protected abstract fun getDateComponent(date: Date): String

    /**
     * Returns the name of the component to be displayed along side the date component.
     *
     * @return The name of the component label, e.g. Year, Month, Day
     */
    protected abstract fun getDateComponentLabel(): String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDateComponentBinding.inflate(inflater, container, false)
        val surfaceColor3 = SurfaceColors.SURFACE_1.getColor(requireContext())

        binding.verifiedLayout.setBackgroundColor(surfaceColor3)
        binding.dateComponentLabel.text = getDateComponentLabel()

        model.cutoffDate.observe(viewLifecycleOwner) {
            it?.let {
                binding.dateComponent.text = getDateComponent(it)
            }
        }

        return binding.root
    }
}