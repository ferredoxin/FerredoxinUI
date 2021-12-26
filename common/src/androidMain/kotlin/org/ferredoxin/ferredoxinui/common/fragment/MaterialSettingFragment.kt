package org.ferredoxin.ferredoxinui.common.fragment

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.preference.*
import org.ferredoxin.ferredoxinui.common.activity.MaterialSettingActivity
import org.ferredoxin.ferredoxinui.common.base.*

class MaterialSettingFragment : PreferenceFragmentCompat(), TitleAble {

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

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = null
        preferenceScreen = preferenceManager.createPreferenceScreen(requireContext())
        addViewInUiGroup(uiScreen, preferenceScreen)
    }

    private fun addViewInUiGroup(uiGroup: UiGroup, preferenceGroup: PreferenceGroup) {
        for (uiDescription in uiGroup.contains.values) {
            when {
                uiDescription is UiCategory -> {
                    val preferenceCategory = PreferenceCategory(requireContext()).apply {
                        title = uiDescription.nameProvider.getValue(requireContext())
                    }
                    preferenceGroup.addPreference(preferenceCategory)
                    if (uiDescription.contains.isNotEmpty()) {
                        addViewInUiGroup(uiDescription, preferenceCategory)
                    }
                }
                uiDescription is UiScreen -> {
                    preferenceGroup.addPreference(Preference(requireContext()).apply {
                        title = uiDescription.nameProvider.getValue(requireContext())
                        summary = uiDescription.summaryProvider.getValue(requireContext())
                        onPreferenceClickListener = getOnClickListener(ClickToNewSetting(uiDescription), uiDescription.name)
                    })
                }
                uiDescription is UiPreference -> {
                    when (uiDescription) {
                        is UiClickableSwitchPreference -> {
                            preferenceGroup.addPreference(SwitchPreferenceCompat(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                setOnPreferenceChangeListener { _: Preference, any: Any ->
                                    val bool = any as? Boolean
                                    if (bool != null) {
                                        uiDescription.value.value = bool
                                    }
                                    true
                                }
                                isEnabled = uiDescription.valid
                                observeStateFlow(uiDescription.value) {
                                    isChecked = it ?: isChecked
                                }
                                onPreferenceClickListener = getOnClickListener(uiDescription.onClickListener, uiDescription.title)
                            })
                        }
                        is UiSwitchPreference -> {
                            preferenceGroup.addPreference(SwitchPreferenceCompat(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                setOnPreferenceChangeListener { _: Preference, any: Any ->
                                    val bool = any as? Boolean
                                    if (bool != null) {
                                        uiDescription.value.value = bool
                                    }
                                    true
                                }
                                isEnabled = uiDescription.valid
                                observeStateFlow(uiDescription.value) {
                                    isChecked = it ?: isChecked
                                }
                            })
                        }
                        is UiChangeablePreference<*> -> {
                            preferenceGroup.addPreference(Preference(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                isEnabled = uiDescription.valid
                                observeStateFlow(uiDescription.value) {
                                    summary = it?.toString()
                                }
                                onPreferenceClickListener = getOnClickListener(uiDescription.onClickListener, uiDescription.title)
                            })
                        }
                        is UiPreference -> {
                            preferenceGroup.addPreference(Preference(requireContext()).apply {
                                title = uiDescription.titleProvider.getValue(requireContext())
                                summary = uiDescription.summaryProvider.getValue(requireContext())
                                isEnabled = uiDescription.valid
                                onPreferenceClickListener = getOnClickListener(uiDescription.onClickListener, uiDescription.title)
                            })
                        }
                    }
                }
            }
        }
    }

    fun setUiScreen(uiScreen: UiScreen): MaterialSettingFragment {
        this.uiScreen = uiScreen
        this.title = uiScreen.name
        return this
    }

    private fun getOnClickListener(listener: (FragmentActivity) -> Boolean, title: String): Preference.OnPreferenceClickListener {
        return when (listener) {
            is ClickToNewSetting -> {
                Preference.OnPreferenceClickListener {
                    (requireActivity() as MaterialSettingActivity<*>).changeFragment(MaterialSettingFragment().setUiScreen(listener.uiScreen))
                    true
                }
            }
            is ClickToNewPages -> {
                Preference.OnPreferenceClickListener {
                    (requireActivity() as MaterialSettingActivity<*>).changeFragment(listener.viewMap, title)
                    true
                }
            }
            else -> {
                Preference.OnPreferenceClickListener {
                    listener.invoke(requireActivity())
                }
            }
        }
    }

}