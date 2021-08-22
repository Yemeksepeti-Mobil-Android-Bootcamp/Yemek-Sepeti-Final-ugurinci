package com.ugurinci.yemeksepetifinal.ui.restaurantlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurinci.yemeksepetifinal.databinding.FragmentRestaurantListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private var _binding: FragmentRestaurantListBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantRecyclerViewAdapter: RestaurantRecyclerViewAdapter

    private val viewModel: RestaurantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel.restaurants.observe(viewLifecycleOwner, {
                if (it != null) {
                    restaurantRecyclerViewAdapter = RestaurantRecyclerViewAdapter(it)
                    restaurantRecyclerViewAdapter.itemClickListener = { restaurantItem ->
                        val action =
                            RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                                restaurantItem.id
                            )
                        findNavController().navigate(action)
                    }
                    recyclerViewRestaurant.adapter = restaurantRecyclerViewAdapter
                } else {
                    Snackbar.make(view, "mockAPI is unavailable", Snackbar.LENGTH_SHORT).show()
                }
            })

            buttonRestaurantList.setOnClickListener {
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToCartFragment()
                findNavController().navigate(action)
            }

            imageButtonRestaurantListProfile.setOnClickListener {
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToProfileFragment()
                findNavController().navigate(action)
            }

            imageButtonRestaurantListLogout.setOnClickListener {
                Firebase.auth.signOut()
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToLoginFragment()
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