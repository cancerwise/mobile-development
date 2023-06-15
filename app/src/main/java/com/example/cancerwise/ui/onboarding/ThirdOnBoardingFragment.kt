package com.example.cancerwise.ui.onboarding

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cancerwise.R

class ThirdOnBoardingFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboardning3, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        if (viewPager?.currentItem == 2){
            Handler().postDelayed({
                viewPager?.currentItem = 0
            }, 4000)
        }
//        view.next.setOnClickListener {
//            viewPager?.currentItem = 1
//        }

        return view
    }
}
