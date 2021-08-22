package com.ugurinci.yemeksepetifinal.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ugurinci.yemeksepetifinal.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        binding.apply {
            buttonLogin.setOnClickListener {
                val email = editTextLoginEmail.text.toString()
                if (email.isEmpty()) {
                    editTextLoginEmail.error = "please enter your email"
                }
                val password = editTextLoginPassword.text.toString()
                if (password.isEmpty()) {
                    editTextLoginPassword.error = "please enter your password"
                }
                if (!(email.isEmpty() || password.isEmpty())) {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment()
                            findNavController().navigate(action)
                            Toast.makeText(context, "Login is successful.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            buttonLoginRegister.setOnClickListener {
                val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}