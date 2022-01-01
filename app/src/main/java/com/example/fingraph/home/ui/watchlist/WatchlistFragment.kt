package com.example.fingraph.home.ui.watchlist

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
import com.example.fingraph.databinding.FragmentWatchlistBinding
import com.example.fingraph.home.ui.watchlist.view.WatchlistRecyclerAdapter

class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel
    private var adapter: RecyclerView.Adapter<WatchlistRecyclerAdapter.ViewHolder>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var _binding: FragmentWatchlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        watchlistViewModel =
            ViewModelProvider(this).get(WatchlistViewModel::class.java)

        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        watchlistViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.watchlist_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = WatchlistRecyclerAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}