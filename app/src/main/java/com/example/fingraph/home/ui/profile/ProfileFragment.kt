package com.example.fingraph.home.ui.profile

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fingraph.R
import com.example.fingraph.databinding.FragmentProfileBinding
import com.example.fingraph.home.ui.news.NewsActivity
import com.example.fingraph.home.ui.watchlist.AddCryptoDialogFragment
import com.example.fingraph.login.LoginActivity
import com.example.fingraph.utils.data.SharedPreferencesManager
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textProfile
        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val logoutButton = root.findViewById<View>(R.id.logout_button)

        logoutButton.setBackgroundColor(resources.getColor(R.color.design_primary))
        logoutButton.setOnClickListener {
            view?.let { it1 ->
                Snackbar.make(it1, "Logged Out!", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null)
                    .show()
                val intent = Intent(context, LoginActivity::class.java)
                context?.startActivity(intent)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}