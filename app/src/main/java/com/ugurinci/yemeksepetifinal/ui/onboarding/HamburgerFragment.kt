package com.ugurinci.yemeksepetifinal.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ugurinci.yemeksepetifinal.databinding.FragmentHamburgerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HamburgerFragment : Fragment() {

    private var _binding: FragmentHamburgerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHamburgerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            buttonHamburger.setOnClickListener {
                val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}