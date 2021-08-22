package com.ugurinci.yemeksepetifinal.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ugurinci.yemeksepetifinal.databinding.FragmentPizzaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private var _binding: FragmentPizzaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPizzaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}