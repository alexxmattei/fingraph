package com.example.fingraph.home

import android.content.Context
import android.content.res.ColorStateList
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.adapters.tags.SelectTagsAdapter
import com.example.fingraph.models.search.Tag
import com.example.fingraph.utils.views.SelectTagItemDecorator
import com.example.fingraph.utils.views.getTextChangeStateFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.Dispatchers

class SearchCryptocurrenciesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tagsAdapter = SelectTagsAdapter(this::onTagsSelected)
    private val backButton: ImageView = createBackButton()
    private val searchBar: EditText = createSearchBar()
    private val clearIcon: ImageView = createClearIcon()
    private val tagsList: RecyclerView = createTagsList()

    private var selectedSearchTag: Tag? = null

    val searchFlow: Flow<String> by lazy { createSearchFlow() }
    var onBackPressed: (() -> Unit)? = null

    init {
        addView(backButton)
        addView(searchBar)
        addView(clearIcon)
        addView(tagsList)
        createLayoutConstraints()
        backButton.setOnClickListener { onBackPressed?.invoke() }
    }

    fun clearSearch() {
        searchBar.setText("")
        checkIfTagExistsInSearchBar("")
    }

    private fun createBackButton(): ImageView {
        val buttonSize = 40 // TODO check if it works as an Int and collects data as dp
        val background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_search_bar, null)
        val icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_left, null)
        val tint = ResourcesCompat.getColor(resources, R.color.design_primary_color_accent, null)

        return ImageView(context).apply {
            id = R.id.icon_view_back_button
            layoutParams = LayoutParams(buttonSize, buttonSize)
            setBackground(background)
            setImageDrawable(icon)
            scaleType = ImageView.ScaleType.CENTER
            imageTintList = ColorStateList.valueOf(tint)
        }

    }

    private fun createSearchBar(): EditText {
        val background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_search_bar, null)
        val textColorInSearchBar =
            ResourcesCompat.getColor(resources, R.color.design_black_text, null)
        val padding = 14
        val iconSize = 14
        val textSize = 16.0f
        val searchIconMargin = 5

        return EditText(context).apply {
            id = R.id.edit_text_search
            layoutParams = LayoutParams(0, 0).apply {
                setPadding(padding, 0, iconSize + searchIconMargin, 0)
            }
            setBackground(background)
            hint = "Search"
            setTextSize(COMPLEX_UNIT_PX, textSize)
            maxLines = 1
            inputType = InputType.TYPE_CLASS_TEXT
            imeOptions = EditorInfo.IME_ACTION_DONE
            setTextColor(textColorInSearchBar)
            setHintTextColor(textColorInSearchBar)
        }
    }

    private fun createClearIcon(): ImageView {
        val paddingSide = 5
        val paddingTop = 9
        val clearSearchBarIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_close, null)
        val tint = ResourcesCompat.getColor(resources, R.color.design_primary_text, null)

        return ImageView(context).apply {
            id = R.id.image_view_clear_search
            layoutParams = LayoutParams(WRAP_CONTENT, 0).apply {
                setPadding(paddingSide, paddingTop, paddingSide, paddingTop)
            }
            setImageDrawable(clearSearchBarIcon)
            imageTintList = ColorStateList.valueOf(tint)
            setOnClickListener { clearSearch() }
        }
    }

    private fun createTagsList(): RecyclerView {
        val searchBarHeight = 35
        return RecyclerView(context).apply {
            id = R.id.recycler_view_tags
            layoutParams = LayoutParams(MATCH_PARENT, searchBarHeight)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            addItemDecoration(SelectTagItemDecorator(context))
            adapter = tagsAdapter
        }
    }

    private fun createSearchFlow(): Flow<String> {
        return searchBar.getTextChangeStateFlow().debounce(300).map { it.trim().lowercase() }
            .flowOn(Dispatchers.Main).onEach(this::checkIfTagExistsInSearchBar)
    }

    private fun createLayoutConstraints() {
        val marginTop = 20
        val marginSides = 10
        val searchIconMargin = 5

        val constraintSet = ConstraintSet()
        constraintSet.apply {
            clone(this@SearchCryptocurrenciesView)

            connect(backButton.id, START, PARENT_ID, START, marginSides)
            connect(backButton.id, TOP, PARENT_ID, TOP, marginTop)

            connect(searchBar.id, TOP, backButton.id, TOP)
            connect(searchBar.id, BOTTOM, backButton.id, TOP)
            connect(searchBar.id, START, backButton.id, END, marginSides / 2)
            connect(searchBar.id, END, PARENT_ID, END, marginSides)

            connect(clearIcon.id, TOP, searchBar.id, TOP)
            connect(clearIcon.id, BOTTOM, searchBar.id, BOTTOM)
            connect(clearIcon.id, END, searchBar.id, END, searchIconMargin)

            connect(tagsList.id, TOP, searchBar.id, BOTTOM, marginTop)
            connect(tagsList.id, BOTTOM, PARENT_ID, BOTTOM, marginTop)
            connect(tagsList.id, START, searchBar.id, START, marginSides)
            connect(tagsList.id, END, searchBar.id, END, marginSides)

            applyTo(this@SearchCryptocurrenciesView)
        }

    }

    private fun onTagsSelected(tag: Tag) {
        val tags = tagsAdapter.items
        tags.firstOrNull { it.isSelected }?.let {
            it.isSelected = false
            val index = tags.indexOf(it)
            tagsAdapter.notifyItemChanged(index)
        }
        tag.isSelected = true
        tagsAdapter.notifyItemChanged(tags.indexOf(tag))
        selectedSearchTag = tag

        searchBar.setText(tag.value)
        searchBar.setSelection(tag.value.length)
    }

    private fun checkIfTagExistsInSearchBar(searchTerm: String) {
        val tag = selectedSearchTag ?: return
        if (searchTerm == tag.value) return
        tag.isSelected = false
        val indexOfSelectedTag = tagsAdapter.items.indexOf(tag)
        tagsAdapter.notifyItemChanged(indexOfSelectedTag)
        selectedSearchTag = null
    }

}