package com.example.fingraph.home.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.databinding.FragmentNewsBinding
import com.example.fingraph.home.ItemViewModel
import com.example.fingraph.home.ui.news.view.NewsRecyclerAdapter
import com.example.fingraph.utils.data.SharedPreferencesManager

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this, viewModelFactory {
            ItemViewModel()
        })[ItemViewModel::class.java]

        viewModel.cryptoNewsFlowData.observe(viewLifecycleOwner, {
            it
        })
        viewModel.fetchCryptoNewsData()

        view.findViewById<RecyclerView>(R.id.news_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NewsRecyclerAdapter(SharedPreferencesManager.currentNews)
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