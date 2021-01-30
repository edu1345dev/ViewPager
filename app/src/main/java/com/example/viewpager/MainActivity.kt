package com.example.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager.ui.main.PlaceholderFragment
import com.example.viewpager.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.rd.PageIndicatorView

class MainActivity : AppCompatActivity() {

    val viewPager by lazy { findViewById<ViewPager2>(R.id.view_pager) }
    val tabs by lazy { findViewById<TabLayout>(R.id.tabs) }
    val indicator by lazy { findViewById<PageIndicatorView>(R.id.pageIndicator) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val fragments = getFragments()

        val sectionsPagerAdapter = SectionsPagerAdapter(fragments, this)

        viewPager.adapter = sectionsPagerAdapter

        indicator.count = fragments.size

        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicator.setSelected(position)
                }
            })

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = fragments[position].arguments?.getString(
                PlaceholderFragment.FRAGMENT_NAME,
                "Empty name"
            )
        }.attach()
    }

    private fun getFragments(): List<Fragment> {
        return listOf(
            PlaceholderFragment.newInstance(
                "First Fragment",
                R.color.design_default_color_on_secondary
            ),
            PlaceholderFragment.newInstance(
                "Second Fragment",
                R.color.design_default_color_error
            ),
            PlaceholderFragment.newInstance(
                "Third Fragment",
                R.color.design_default_color_primary
            )
        )
    }

}