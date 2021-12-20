package org.ferredoxin.ferredoxinui.qnotified_style.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import org.ferredoxin.ferredoxinui.common.base.*
import org.ferredoxin.ferredoxinui.qnotified_style.activity.MaiTungTMStyleActivity
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.FragmentEmptyBinding
import org.ferredoxin.ferredoxinui.qnotified_style.databinding.FragmentSettingsBinding
import org.ferredoxin.ferredoxinui.qnotified_style.item.*
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class MaiTungTMSettingFragment : Fragment(), TitleAble {

    private lateinit var uiScreen: UiScreen
    override lateinit var title: String
    private lateinit var titleProviderCache: ResourceProvider<String>
    override var titleProvider: ResourceProvider<String>
        get() {
            if (this::titleProviderCache.isInitialized) {
                return titleProviderCache
            } else {
                return DirectResourceProvider(title)
            }
        }
        set(value) {
            titleProviderCache = value
        }
    private lateinit var binding: ViewBinding
    private var viewCache: WeakReference<View>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = viewCache?.get()
        if (view != null) {
            val parent = view.parent as ViewGroup?
            parent?.removeView(view)
            return view
        }

        if (!::uiScreen.isInitialized) {
            requireActivity().recreate()
            return null
        }

        if (uiScreen.contains.isEmpty()) {
            binding = FragmentEmptyBinding.inflate(layoutInflater, container, false)
        } else {
            binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
            thread {
                requireActivity().runOnUiThread {
                    addViewInUiGroup(uiScreen, (binding as FragmentSettingsBinding).linearContainer)
                }
            }
        }

        viewCache = WeakReference(binding.root)

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
                uiDescription is UiAboutItem -> {
                    val about = About(requireContext())
                    viewGroup.addView(about)
                    about.setTitle(uiDescription.titleProvider.getValue(requireContext()))
                    about.setIcon(uiDescription.icon.invoke(requireContext()))
                }
                uiDescription is UiCategory -> {
                    if (!uiDescription.noTitle) {
                        viewGroup.addView(Subtitle(requireContext()).apply {
                            setTitle(uiDescription.nameProvider.getValue(requireContext()))
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
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                onValueChangedListener = {
                                    uiDescription.value.value = it
                                }
                                enable = uiDescription.valid
                                observeStateFlow(uiDescription.value) {
                                    checked = it ?: checked
                                }
                                setOnClickListener(getOnClickListener(uiDescription.onClickListener, uiDescription.title))
                            })
                        }
                        is UiSwitchPreference -> {
                            viewGroup.addView(SwitchItem(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                onValueChangedListener = {
                                    uiDescription.value.value = it
                                }
                                enable = uiDescription.valid
                                observeStateFlow(uiDescription.value) {
                                    checked = it ?: checked
                                }
                            })
                        }
                        is UiChangeablePreference<*> -> {
                            viewGroup.addView(ClickableItem(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                enable = uiDescription.valid
                                setOnClickListener(getOnClickListener(uiDescription.onClickListener, uiDescription.title))
                                observeStateFlow(uiDescription.value) {
                                    value = it?.toString()
                                }
                            })
                        }
                        is UiPreference -> {
                            viewGroup.addView(ClickableItem(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                clickAble = uiDescription.clickAble
                                subSummary = uiDescription.subSummary
                                setOnClickListener(getOnClickListener(uiDescription.onClickListener, uiDescription.title))
                                enable = uiDescription.valid
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