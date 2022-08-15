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

    // Reference to the view binding object
    // Only defined between onCreateView and onDestroyView
    private var _binding: FragmentDateComponentBinding? = null
    private val binding get() = _binding!!

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

    /**
     * @see [onCreateView]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDateComponentBinding.inflate(inflater, container, false)

        binding.verifiedLayout?.run {
            setBackgroundColor(SurfaceColors.SURFACE_1.getColor(requireContext()))
        }

        binding.dateComponentLabel.text = getDateComponentLabel()

        return binding.root
    }

    /**
     * @see [onDestroyView]
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * @see [onStart]
     */
    override fun onStart() {
        super.onStart()
        model.cutoffDate.observe(viewLifecycleOwner) {
            it?.let {
                binding.dateComponent.text = getDateComponent(it)
            }
        }
    }

    /**
     * @see [onStop]
     */
    override fun onStop() {
        super.onStop()
        model.cutoffDate.removeObservers(viewLifecycleOwner)
    }
}