package org.kitsunepie.maitungtmui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.kitsunepie.maitungtmui.activity.MaiTungTMStyleActivity
import org.kitsunepie.maitungtmui.base.*
import org.kitsunepie.maitungtmui.databinding.FragmentSettingsBinding
import org.kitsunepie.maitungtmui.item.*

class MaiTungTMSettingFragment : Fragment(), TitleAble {

    private lateinit var uiScreen: UiScreen
    override lateinit var title: String
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::uiScreen.isInitialized) {
            requireActivity().recreate()
            return null
        }
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        if (uiScreen.contains.isEmpty()) {
            binding.emptyView.visibility = View.VISIBLE
        } else {
            viewLifecycleOwner.lifecycleScope.launch {
                addViewInUiGroup(uiScreen, binding.linearContainer)
            }
        }
        return binding.root
    }

    fun setUiScreen(uiScreen: UiScreen): MaiTungTMSettingFragment {
        this.uiScreen = uiScreen
        this.title = uiScreen.name
        return this
    }

    private fun addViewInUiGroup(uiGroup: UiGroup, viewGroup: ViewGroup) {
        //Log.d(this::class.java.simpleName, "Adding: " + uiGroup.name + " = " + uiGroup.contains.toString())
        for (uiDescription in uiGroup.contains.values) {
            //Log.d(this::class.java.simpleName, "Adding: $uiDescription")
            when {
                uiDescription is UiCategory -> {
                    if (!uiDescription.noTitle) {
                        viewGroup.addView(Subtitle(requireContext()).apply {
                            setTitle(uiDescription.name)
                        })
                    }
                    if (uiDescription.contains.isNotEmpty()) {
                        val category = Category(requireContext())
                        viewGroup.addView(category)
                        addViewInUiGroup(uiDescription, category.linearLayout)
                    }
                }
                uiDescription is UiPreference -> {
                    when (uiDescription) {
                        is UiClickableSwitchPreference -> {
                            viewGroup.addView(ClickableSwitchItem(requireContext()).apply {
                                title = uiDescription.title
                                summary = uiDescription.summary
                                onValueChangedListener = {
                                    uiDescription.value.value = it
                                }
                                enable = uiDescription.enable
                                uiDescription.value.observe(viewLifecycleOwner) {
                                    checked = it
                                }
                                setOnClickListener(
                                    getOnClickListener(
                                        uiDescription.onClickListener,
                                        uiDescription.title
                                    )
                                )
                            })
                        }
                        is UiSwitchPreference -> {
                            viewGroup.addView(SwitchItem(requireContext()).apply {
                                title = uiDescription.title
                                summary = uiDescription.summary
                                onValueChangedListener = {
                                    uiDescription.value.value = it
                                }
                                enable = uiDescription.enable
                                uiDescription.value.observe(viewLifecycleOwner) {
                                    checked = it
                                }
                            })
                        }
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
                                setOnClickListener(
                                    getOnClickListener(
                                        uiDescription.onClickListener,
                                        uiDescription.title
                                    )
                                )
                                enable = uiDescription.enable
                            })
                        }
                    }
                }
            }
        }
    }

    private fun getOnClickListener(
        listener: (FragmentActivity) -> Boolean,
        title: String
    ): View.OnClickListener {
        return when (listener) {
            is ClickToNewSetting -> {
                View.OnClickListener {
                    (requireActivity() as MaiTungTMStyleActivity<*>).addFragment(
                        MaiTungTMSettingFragment().setUiScreen(
                            listener.uiScreen
                        )
                    )
                }
            }
            is ClickToNewPages -> {
                View.OnClickListener {
                    (requireActivity() as MaiTungTMStyleActivity<*>).addFragment(
                        ViewPagerFragment().setViewMap(
                            listener.viewMap
                        ).setTitle(title)
                    )
                }

            }
            else -> {
                View.OnClickListener {
                    listener.invoke(requireActivity())
                }
            }
        }
    }

}