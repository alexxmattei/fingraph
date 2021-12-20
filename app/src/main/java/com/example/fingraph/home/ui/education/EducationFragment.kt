package com.example.fingraph.home.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fingraph.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {

    private lateinit var educationViewModel: EducationViewModel
    private var _binding: FragmentEducationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        educationViewModel =
            ViewModelProvider(this).get(EducationViewModel::class.java)

        _binding = FragmentEducationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textEducation
        educationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}