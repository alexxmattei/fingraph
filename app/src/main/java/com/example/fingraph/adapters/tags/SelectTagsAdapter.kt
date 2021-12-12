package com.example.fingraph.adapters.tags

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fingraph.R
import com.example.fingraph.models.search.Tag
import kotlin.properties.Delegates

class SelectTagsAdapter(private val onTagSelected: (Tag) -> Unit) :
    RecyclerView.Adapter<SelectTagsAdapter.ViewHolder>() {

    var items: List<Tag> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(createView(parent.context))
    }

    private fun createView(context: Context): View {
        val textPadding = 6
        return FrameLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(TextView(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, 32
                ).apply {
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                    gravity = Gravity.CENTER
                    setPadding(textPadding, 0, textPadding, 0)
                }
                gravity = Gravity.CENTER
            })
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onTagSelected)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tag: Tag, onTagSelected: (Tag) -> Unit) {
            ((itemView as ViewGroup).getChildAt(0) as TextView).apply {
                text = tag.value
                setTextColor(
                    if (tag.isSelected) {
                        resources.getColor(R.color.design_primary, null)
                    } else {
                        resources.getColor(R.color.design_primary_text, null)
                    }
                )
                background = ResourcesCompat.getDrawable(
                    itemView.resources,
                    R.drawable.background_rounded_100,
                    null
                )
                    .apply {
                        (this as? GradientDrawable)?.apply {
                            cornerRadius = 5.0f
                            setStroke(
                                1, resources.getColor(R.color.design_primary_text, null)
                            )
                            this.setColor(
                                if (tag.isSelected) {
                                    resources.getColor(R.color.design_primary_text, null)
                                } else {
                                    resources.getColor(R.color.design_primary)
                                }
                            )
                        }
                    }
                setOnClickListener {
                    onTagSelected(tag)
                }
            }
        }
    }
}