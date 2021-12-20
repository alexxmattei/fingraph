package com.example.fingraph.home

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fingraph.R
import com.example.fingraph.base.BaseActivity
import com.example.fingraph.base.bindView
import com.example.fingraph.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity() {

    private val rootView by bindView<ConstraintLayout>(R.id.container)
    private val titleHeaderContainer by bindView<View>(R.id.linear_layout_title_container)
    private val titleText by bindView<TextView>(R.id.tv_title)
    private val subtitleText by bindView<TextView>(R.id.tv_sub_title)
    private val rightButton by bindView<ImageView>(R.id.icon_view_right_button)
    private val tabLayout by bindView<TabLayout>(R.id.tab_layout_navigate_home)
    private val tabLayoutContainer: FrameLayout by bindView(R.id.frame_layout_tab_container_home)
    private val searchView: SearchCryptocurrenciesView by bindView(R.id.search_view_cryptocurrencies)
    private val deleteSearchQueryItemView: View by bindView(R.id.frame_layout_search_delete)
    private lateinit var binding: ActivityMainBinding

    private val container: FrameLayout by bindView(R.id.frame_layout_container)
    private val currentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationView: BottomNavigationView = binding.navigationView
        val navigationController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_education,
                R.id.navigation_notifications,
                R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navigationController, appBarConfiguration)
        navigationView.setupWithNavController(navigationController)
    }
}