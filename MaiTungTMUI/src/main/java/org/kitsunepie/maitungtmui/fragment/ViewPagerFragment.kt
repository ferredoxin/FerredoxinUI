package org.kitsunepie.maitungtmui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
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