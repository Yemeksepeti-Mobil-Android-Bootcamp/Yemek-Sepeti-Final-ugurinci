package com.ugurinci.yemeksepetifinal.ui.fooddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurinci.yemeksepetifinal.databinding.FragmentFoodDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {

    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!

    private val args: FoodDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: FoodDetailViewModel.ViewModelFactory

    private val viewModel: FoodDetailViewModel by viewModels {
        FoodDetailViewModel.provideFactory(viewModelFactory, args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.food.observe(viewLifecycleOwner, {
                textViewFoodDetailName.text = it.name
                textViewFoodDetailPrice.text = it.price.toString() + " TL"
                textViewFoodDetailExplanation.text = it.explanation
                Glide.with(root.context).load(it.image).into(imageViewFoodDetail)
            })

            buttonFoodDetailAddCart.setOnClickListener {
                viewModel.addCart()
                Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
            }

            buttonFoodDetailGoCart.setOnClickListener {
                val action = FoodDetailFragmentDirections.actionFoodDetailFragmentToCartFragment()
                findNavController().navigate(action)
            }

            imageButtonFoodDetailProfile.setOnClickListener {
                val action =
                    FoodDetailFragmentDirections.actionFoodDetailFragmentToProfileFragment()
                findNavController().navigate(action)
            }

            imageButtonFoodDetailLogout.setOnClickListener {
                Firebase.auth.signOut()
                val action = FoodDetailFragmentDirections.actionFoodDetailFragmentToLoginFragment()
                findNavController().navigate(action)
                Toast.makeText(context, "Logged out.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}