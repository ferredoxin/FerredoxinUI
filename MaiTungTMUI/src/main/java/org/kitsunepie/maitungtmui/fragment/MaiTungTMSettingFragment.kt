package org.kitsunepie.maitungtmui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.base.UiCategory
import org.kitsunepie.maitungtmui.base.UiGroup
import org.kitsunepie.maitungtmui.base.UiPreference
import org.kitsunepie.maitungtmui.base.UiScreen
import org.kitsunepie.maitungtmui.item.Category
import org.kitsunepie.maitungtmui.item.ClickableItem
import org.kitsunepie.maitungtmui.item.Subtitle

class MaiTungTMSettingFragment : Fragment() {

    private lateinit var uiScreen: UiScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val linearLayout = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        addViewInUiGroup(uiScreen, linearLayout)
        return linearLayout;
    }

    fun setUiScreen(uiScreen: UiScreen): MaiTungTMSettingFragment {
        this.uiScreen = uiScreen
        return this
    }

    private fun addViewInUiGroup(uiGroup: UiGroup, viewGroup: ViewGroup) {
        Log.d(
            this::class.java.simpleName,
            "Adding: " + uiGroup.name + " = " + uiGroup.contains.toString()
        )
        for (uiDescription in uiGroup.contains.values) {
            Log.d(this::class.java.simpleName, "Adding: $uiDescription")
            when {
                uiDescription is UiCategory -> {
                    viewGroup.addView(Subtitle(requireContext()).apply {
                        setTitle(uiDescription.name)
                    })
                    if (uiDescription.contains.isNotEmpty()) {
                        val category = Category(requireContext())
                        viewGroup.addView(category)
                        addViewInUiGroup(uiDescription, category.linearLayout)
                    }
                }
                uiDescription is UiPreference -> {
                    when (uiDescription) {
                        is UiPreference -> {
                            viewGroup.addView(ClickableItem(requireContext()).apply {
                                setTitle(uiDescription.title)
                                setOnClickListener {
                                    uiDescription.onClickListener(requireContext())
                                }
                            })
                        }
                    }
                }
            }
        }
    }

}