package com.example.fingraph.home.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.databinding.FragmentEducationBinding
import com.example.fingraph.home.ItemViewModel
import com.example.fingraph.home.ui.education.view.EducationRecyclerAdapter
import com.example.fingraph.home.ui.watchlist.view.WatchlistRecyclerAdapter
import com.example.fingraph.utils.data.SharedPreferencesManager

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
        val viewModel = ViewModelProvider(this, viewModelFactory {
            ItemViewModel()
        })[ItemViewModel::class.java]

        viewModel.cryptoMetadataFlowData.observe(viewLifecycleOwner, {
            it
        })
        viewModel.fetchCryptoMetadata()

        view.findViewById<RecyclerView>(R.id.education_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = EducationRecyclerAdapter(SharedPreferencesManager.currentEducationList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
        }
}