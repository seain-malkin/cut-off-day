package me.seain.cutoffday.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import me.seain.cutoffday.R
import me.seain.cutoffday.databinding.FragmentStatusBinding
import me.seain.cutoffday.viewmodel.MainViewModel

/**
 * Fragment that displays the cutoff-age over the bottom app bar.
 *
 * @author Seain Malkin
 */
class StatusFragment : Fragment() {

    private val model: MainViewModel by activityViewModels()

    // Reference to the view binding object
    // Only defined between onCreateView and onDestroyView
    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!

    /**
     * Changes the displayed age in the control bar
     *
     * @param value The new age to display in the UI
     */
    private fun updateAge(value: Int) {
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
    ): View {
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

    /**
     * @see [onStart]
     */
    override fun onStart() {
        super.onStart()
        model.cutoffAge.observe(viewLifecycleOwner) {
            it?.let {
                updateAge(it)
            }
        }
    }

    /**
     * @see [onStop]
     */
    override fun onStop() {
        super.onStop()
        model.cutoffAge.removeObservers(viewLifecycleOwner)
    }
}