package com.example.fingraph.home.ui.watchlist

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.databinding.FragmentWatchlistBinding
import com.example.fingraph.home.ItemViewModel
import com.example.fingraph.home.ui.watchlist.view.WatchlistRecyclerAdapter
import com.example.fingraph.utils.data.SharedPreferencesManager
import com.google.android.material.snackbar.Snackbar

var searchQueryText = "BTC"

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
        val addCryptoButton = root.findViewById<View>(R.id.add_crypto_button)

        addCryptoButton.setBackgroundColor(resources.getColor(R.color.design_primary))
        addCryptoButton.setOnClickListener {
            view?.let { it1 ->
                Snackbar.make(it1, "Add Cryptocurrencies", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
                    .show()
                AddCryptoDialogFragment().show(
                    childFragmentManager, AddCryptoDialogFragment.TAG)

            }

        }

        watchlistViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this, viewModelFactory {
            ItemViewModel()
        })[ItemViewModel::class.java]

        viewModel.cryptoPriceFlowData.observe(viewLifecycleOwner, {
            it
        })
        viewModel.fetchCryptoPriceData()

        view.findViewById<RecyclerView>(R.id.watchlist_recycler_view).apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = WatchlistRecyclerAdapter(SharedPreferencesManager.currentPrices)
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

    fun showAlertDialog(){
        val alertDialogBuilder: AlertDialog.Builder = android.app.AlertDialog.Builder(activity?.baseContext)
        alertDialogBuilder.setTitle("Add Crypto to Watchlist")

        val cryptoSearchQuery = EditText(activity?.baseContext)

        cryptoSearchQuery.hint = "Type a crypto symbol"
        cryptoSearchQuery.inputType = InputType.TYPE_CLASS_TEXT
        alertDialogBuilder.setView(cryptoSearchQuery)

        alertDialogBuilder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            searchQueryText = cryptoSearchQuery.text.toString()
        })
        alertDialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        alertDialogBuilder.show()
    }

}

class AddCryptoDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Add crypto to watchlist")
            .setPositiveButton("Ok") { _,_ ->  }
            .setNegativeButton("Cancel") { _, _ -> }
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}
