package org.kitsunepie.maitungtmui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import org.kitsunepie.maitungtmui.activity.MaiTungTMStyleActivity
import org.kitsunepie.maitungtmui.base.*
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
                        is UiChangeablePreference<*> -> {
                            viewGroup.addView(ClickableItem(requireContext()).apply {
                                title = uiDescription.title
                                summary = uiDescription.summary
                                enable = uiDescription.enable
                                uiDescription.value.observe(viewLifecycleOwner) {
                                    value = it?.toString()
                                }
                            })
                        }
                        is UiPreference -> {
                            viewGroup.addView(ClickableItem(requireContext()).apply {
                                title = uiDescription.title
                                summary = uiDescription.summary
                                enable = uiDescription.enable
                                when (uiDescription.onClickListener) {
                                    is ClickToNewSetting -> {
                                        setOnClickListener {
                                            (requireActivity() as MaiTungTMStyleActivity).addFragment(
                                                MaiTungTMSettingFragment().setUiScreen(
                                                    (uiDescription.onClickListener as ClickToNewSetting).uiScreen
                                                )
                                            )
                                        }
                                    }
                                    is ClickToNewPages -> {
                                        setOnClickListener {
                                            (requireActivity() as MaiTungTMStyleActivity).addFragment(
                                                ViewPagerFragment().setViewMap(
                                                    (uiDescription.onClickListener as ClickToNewPages).viewMap
                                                )
                                            )
                                        }
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }

}