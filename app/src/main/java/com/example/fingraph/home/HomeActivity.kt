package com.example.fingraph.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fingraph.R
import com.example.fingraph.base.BaseActivity
import com.example.fingraph.base.bindView
import com.google.android.material.tabs.TabLayout

class HomeActivity : BaseActivity() {

    private val rootView by bindView<ConstraintLayout>(R.id.constraint_layout_parent)
    private val titleHeaderContainer by bindView<View>(R.id.linear_layout_title_container)
    private val titleText by bindView<TextView>(R.id.tv_title)
    private val subtitleText by bindView<TextView>(R.id.tv_sub_title)
    private val rightButton by bindView<ImageView>(R.id.icon_view_right_button)
    private val tabLayout by bindView<TabLayout>(R.id.tab_layout_navigate_home)
    private val tabLayoutContainer: FrameLayout by bindView(R.id.frame_layout_tab_container_home)
    private val searchView: SearchCryptocurrenciesView by bindView(R.id.search_view_cryptocurrencies)
    private val deleteSearchQueryItemView: View by bindView(R.id.frame_layout_search_delete)

    private val container: FrameLayout by bindView(R.id.frame_layout_container)
    private val currentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
    }
}