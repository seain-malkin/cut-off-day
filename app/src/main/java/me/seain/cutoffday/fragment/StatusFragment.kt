package me.seain.cutoffday.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import me.seain.cutoffday.R
import me.seain.cutoffday.databinding.FragmentStatusBinding

/**
 * Fragment that displays the cutoff-age over the bottom app bar.
 *
 * @author Seain Malkin
 */
class StatusFragment : Fragment() {

    // Reference to the view binding object
    // Only defined between onCreateView and onDestroyView
    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!

    /**
     * Changes the displayed age in the control bar
     *
     * @param value The new age to display in the UI
     */
    fun updateAge(value: Int) {
        binding.cutoffAge.text = getString(R.string.age_cutoff, value)
        if (binding.cutoffAge.visibility == View.INVISIBLE) {
            binding.cutoffAge.visibility = View.VISIBLE
        }
    }

    /**
     * @see [onCreateView]
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatusBinding.inflate(inflater, container, false)

        binding.cutoffAge.visibility = View.INVISIBLE
        return binding.root
    }

    /**
     * @see [onDestroyView]
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}