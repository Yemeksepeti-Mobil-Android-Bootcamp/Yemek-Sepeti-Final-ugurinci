package com.ugurinci.yemeksepetifinal.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ugurinci.yemeksepetifinal.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = ScreenSlidePagerAdapter(requireActivity())
        binding.apply {
            viewPager.adapter = pagerAdapter
            viewPager.setPageTransformer(DepthPageTransformer())
            wormDotsIndicator.setViewPager2(viewPager)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}