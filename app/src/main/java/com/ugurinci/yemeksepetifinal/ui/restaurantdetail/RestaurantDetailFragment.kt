package com.ugurinci.yemeksepetifinal.ui.restaurantdetail

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
import com.ugurinci.yemeksepetifinal.databinding.FragmentRestaurantDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding get() = _binding!!

    private val args: RestaurantDetailFragmentArgs by navArgs()

    private lateinit var foodRecyclerViewAdapter: FoodRecyclerViewAdapter

    @Inject
    lateinit var viewModelFactory: RestaurantDetailViewModel.ViewModelFactory

    private val viewModel: RestaurantDetailViewModel by viewModels {
        RestaurantDetailViewModel.provideFactory(viewModelFactory, args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewModel.restaurant.observe(viewLifecycleOwner, {
                textViewRestaurantDetailName.text = it.name
                textViewRestaurantDetailPhone.text = it.phone
                textViewRestaurantDetailAddress.text = it.address
                Glide.with(root.context).load(it.image).into(imageViewRestaurantDetail)
            })

            viewModel.foods.observe(viewLifecycleOwner, {
                foodRecyclerViewAdapter = FoodRecyclerViewAdapter(it)
                foodRecyclerViewAdapter.itemClickListener = { foodItem ->
                    val action =
                        RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToFoodDetailFragment(
                            foodItem.id
                        )
                    findNavController().navigate(action)
                }
                recyclerViewFood.adapter = foodRecyclerViewAdapter
            })

            buttonRestaurantDetail.setOnClickListener {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToCartFragment()
                findNavController().navigate(action)
            }

            imageButtonRestaurantDetailProfile.setOnClickListener {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToProfileFragment()
                findNavController().navigate(action)
            }

            imageButtonRestaurantDetailLogout.setOnClickListener {
                Firebase.auth.signOut()
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToLoginFragment()
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