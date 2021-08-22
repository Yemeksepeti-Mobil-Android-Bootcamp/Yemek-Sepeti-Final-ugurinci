package com.ugurinci.yemeksepetifinal.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurinci.yemeksepetifinal.utils.Cart
import com.ugurinci.yemeksepetifinal.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartRecyclerViewAdapter: CartRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            cartRecyclerViewAdapter = CartRecyclerViewAdapter()
            recyclerViewCart.adapter = cartRecyclerViewAdapter
            cartRecyclerViewAdapter.itemClickListener = {
                Cart.foodList.remove(it)
                cartRecyclerViewAdapter.notifyDataSetChanged()
                updateUI()
                Toast.makeText(context, "Food removed.", Toast.LENGTH_SHORT).show()
            }
            updateUI()
            buttonCartOrder.setOnClickListener {
                Cart.foodList.clear()
                cartRecyclerViewAdapter.notifyDataSetChanged()
                updateUI()
                Toast.makeText(context, "Order received.", Toast.LENGTH_SHORT).show()
                val action = CartFragmentDirections.actionCartFragmentToRestaurantListFragment()
                findNavController().navigate(action)
            }

            imageButtonCartProfile.setOnClickListener {
                val action = CartFragmentDirections.actionCartFragmentToProfileFragment()
                findNavController().navigate(action)
            }

            imageButtonCartLogout.setOnClickListener {
                Firebase.auth.signOut()
                val action = CartFragmentDirections.actionCartFragmentToLoginFragment()
                findNavController().navigate(action)
                Toast.makeText(context, "Logged out.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI() {
        binding.apply {
            if (Cart.foodList.size > 0) {
                textViewCartEmpty.visibility = View.INVISIBLE
                imageViewCartEmpty.visibility = View.INVISIBLE
                buttonCartOrder.visibility = View.VISIBLE
            } else {
                textViewCartEmpty.visibility = View.VISIBLE
                imageViewCartEmpty.visibility = View.VISIBLE
                buttonCartOrder.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}