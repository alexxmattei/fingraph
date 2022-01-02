package com.example.fingraph.home.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.databinding.FragmentEducationBinding
import com.example.fingraph.home.ui.education.view.EducationRecyclerAdapter

class EducationFragment : Fragment() {

    private lateinit var educationViewModel: EducationViewModel
    private var adapter: RecyclerView.Adapter<EducationRecyclerAdapter.ViewHolder>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
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

        educationViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.education_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = EducationRecyclerAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}