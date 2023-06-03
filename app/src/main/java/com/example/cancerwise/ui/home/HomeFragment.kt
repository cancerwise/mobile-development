package com.example.cancerwise.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.cancerwise.R
import com.example.cancerwise.data.Resource
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.databinding.FragmentHomeBinding
import com.example.cancerwise.model.FakeRewardDataSource
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.ui.auth.AuthViewModel
import com.example.cancerwise.ui.quizioner.QuizionerViewModel
import com.google.firebase.auth.FirebaseAuth
import java.lang.Math.abs


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var adapter: ProductAdapter

    private val viewModel: QuizionerViewModel by viewModels {
        ViewModelFactory(activity?.application ?: Application())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(FirebaseAuth.getInstance().currentUser?.photoUrl)
            .into(binding.profileImage)

        viewModel.getQuizData()
        viewModel.quizioner.observe(requireActivity()) {
            when (it) {
                is Resource.Error -> {

                }

                is Resource.Loading -> {

                }

                is Resource.Success -> it.data?.let { it1 ->

                }
            }
        }

        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 10000)
            }
        })

        binding.btnClose.setOnClickListener {

            binding.cardWarning.animate()
                .translationX(view.width.toFloat())
                .alpha(0.0f)
                .setDuration(300)
                .setUpdateListener {
                    it.doOnEnd {
                        binding.cardWarning.visibility = View.GONE
                    }
                }
        }

        binding.profileImage.setOnClickListener {
            view.findNavController().navigate(R.id.navigation_notifications)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = binding.viewPager2
        handler = Handler(Looper.myLooper()!!)

        val data = ArrayList<Quizioner>()

        data.addAll(FakeRewardDataSource.dummyProduct)


        adapter = ProductAdapter(data, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

}