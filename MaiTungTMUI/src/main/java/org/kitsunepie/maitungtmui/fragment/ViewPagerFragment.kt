package org.kitsunepie.maitungtmui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.kitsunepie.maitungtmui.base.TitleAble
import org.kitsunepie.maitungtmui.base.UiScreen
import org.kitsunepie.maitungtmui.databinding.FragmentViewPagerBinding

typealias ViewMap = List<Pair<String, UiScreen>>

class ViewPagerFragment : Fragment(), TitleAble {

    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewMap: ViewMap
    override lateinit var title: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::viewMap.isInitialized) {
            requireActivity().recreate()
            return null
        }
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)
        val adapter = Adapter()
        binding.viewPager2.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = viewMap[position].first
        }.attach()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val textView = TextView(requireContext())
                textView.textSize = 20F
                textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                params.gravity = Gravity.CENTER
                textView.layoutParams = params
                textView.text = tab?.text
                tab?.customView = textView
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView = null
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        val firstTab = binding.tabLayout.getTabAt(0)
        val textView = TextView(requireContext())
        textView.textSize = 20F
        textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        textView.layoutParams = params
        textView.text = firstTab?.text
        firstTab?.customView = textView
        return binding.root
    }

    fun setTitle(title: String): ViewPagerFragment {
        this.title = title
        return this
    }

    fun setViewMap(viewMap: ViewMap): ViewPagerFragment {
        this.viewMap = viewMap
        return this
    }

    inner class Adapter : FragmentStateAdapter(this@ViewPagerFragment) {
        override fun getItemCount(): Int = viewMap.count()

        override fun createFragment(position: Int): Fragment {
            return MaiTungTMSettingFragment().setUiScreen(viewMap[position].second)
        }

    }

}