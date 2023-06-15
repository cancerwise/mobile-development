package com.example.cancerwise.ui.home

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.cancerwise.R
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.databinding.FragmentHomeBinding
import com.example.cancerwise.model.FakeRewardDataSource
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.ui.quizioner.QuizionerActivity
import com.example.cancerwise.ui.quizioner.QuizionerActivity.Companion.EXTRA_ID
import com.example.cancerwise.ui.quizioner.QuizionerViewModel
import com.example.cancerwise.utils.Utils
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

        init()
        setUpTransformer()
        setUpBottomDetail()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
//                handler.postDelayed(runnable, 10000)
            }
        })
    }

    private fun setUpBottomDetail() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val data = FakeRewardDataSource.dummyProduct

                binding.apply {
                    tvTitleQuiz.text =  data[position].name
                    tvDesc.text =  data[position].desc
                    tvQuestions.text =  data[position].questions.size.toString()
                    tvTime.text =  data[position].time.toString()
                    tvEnglish.text =  "EN"
                    tvSubTitle.text = data[position].totalCases.toString() + " Total Cases Worldwide"
                    tvDeathRate.text = data[position].deathRate.toString() + "% Global Death Rate"
                    starAdapter(data[position].star)

                    btnTakequiz.setOnClickListener {
                        Utils.DialogController.showDialogConfirmation(
                            requireContext(),
                            "Reminder",
                            "This test is use for first aid purpose, there's no doctor patient relationship being made. Our model calculation is only for next step consideration and not for medical reference"
                        ) {
                            val intent = Intent(requireContext(), QuizionerActivity::class.java)
                                .putExtra(EXTRA_ID, data[position].id)

                            context?.startActivity(intent)
                        }
                    }


                }
                super.onPageSelected(position)
            }
        })

    }

    fun starAdapter(star: Int) {
        when(star) {
            1 -> {
                binding.apply {
                    binding.star1.setImageResource(R.drawable.star_fill)
                    binding.star2.setImageResource(R.drawable.outline_star)
                    binding.star3.setImageResource(R.drawable.outline_star)
                    binding.star4.setImageResource(R.drawable.outline_star)
                    binding.star5.setImageResource(R.drawable.outline_star)
                }
            }

            2 -> {
                binding.apply {
                    binding.star1.setImageResource(R.drawable.star_fill)
                    binding.star2.setImageResource(R.drawable.star_fill)
                    binding.star3.setImageResource(R.drawable.outline_star)
                    binding.star4.setImageResource(R.drawable.outline_star)
                    binding.star5.setImageResource(R.drawable.outline_star)
                }
            }

            3 -> {
                binding.apply {
                    binding.star1.setImageResource(R.drawable.star_fill)
                    binding.star2.setImageResource(R.drawable.star_fill)
                    binding.star3.setImageResource(R.drawable.star_fill)
                    binding.star4.setImageResource(R.drawable.outline_star)
                    binding.star5.setImageResource(R.drawable.outline_star)
                }
            }

            4 -> {
                binding.apply {
                    binding.star1.setImageResource(R.drawable.star_fill)
                    binding.star2.setImageResource(R.drawable.star_fill)
                    binding.star3.setImageResource(R.drawable.star_fill)
                    binding.star4.setImageResource(R.drawable.star_fill)
                    binding.star5.setImageResource(R.drawable.outline_star)
                }
            }

            5 -> {
                binding.apply {
                    binding.star1.setImageResource(R.drawable.star_fill)
                    binding.star2.setImageResource(R.drawable.star_fill)
                    binding.star3.setImageResource(R.drawable.star_fill)
                    binding.star4.setImageResource(R.drawable.star_fill)
                    binding.star5.setImageResource(R.drawable.star_fill)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()

//        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

//        handler.postDelayed(runnable , 2000)
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

        //set up first data
        binding.tvTitleQuiz.text =  data[0].name
        binding.tvDesc.text =  data[0].desc
        binding.tvQuestions.text =  data[0].questions.size.toString()
        binding.tvTime.text =  "5"
        binding.tvEnglish.text =  "EN"

        binding.btnTakequiz.setOnClickListener {
            Utils.DialogController.showDialogConfirmation(
                requireContext(),
                "Reminder",
                "This test is use for first aid purpose, there's no doctor patient relationship being made. Our model calculation is only for next step consideration and not for medical reference"
            ) {
                val intent = Intent(requireContext(), QuizionerActivity::class.java)
                    .putExtra(EXTRA_ID, data[0].id)

                context?.startActivity(intent)
            }
        }
    }

}