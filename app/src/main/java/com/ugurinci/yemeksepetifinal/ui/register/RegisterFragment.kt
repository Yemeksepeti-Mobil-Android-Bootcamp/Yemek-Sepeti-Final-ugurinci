package com.ugurinci.yemeksepetifinal.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ugurinci.yemeksepetifinal.data.remote.User
import com.ugurinci.yemeksepetifinal.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        binding.apply {
            buttonRegister.setOnClickListener {
                val name = editTextRegisterName.text.toString()
                if (name.isEmpty()) {
                    editTextRegisterName.error = "please enter your full name"
                }
                val email = editTextRegisterEmail.text.toString()
                if (email.isEmpty()) {
                    editTextRegisterEmail.error = "please enter your email"
                }
                val password = editTextRegisterPassword.text.toString()
                if (password.isEmpty()) {
                    editTextRegisterPassword.error = "please enter your password"
                }
                val phone = editTextRegisterPhone.text.toString()
                if (phone.isEmpty()) {
                    editTextRegisterPhone.error = "please enter your phone number"
                }
                val address = editTextRegisterAddress.text.toString()
                if (address.isEmpty()) {
                    editTextRegisterAddress.error = "please enter your address"
                }
                if (!(name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty())) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val uid = it.result?.user?.uid.toString()
                            val user = User(uid, name, email, phone, address)
                            db.collection("Users").document(user.uid).set(user)
                                .addOnSuccessListener {
                                    val action =
                                        RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                                    findNavController().navigate(action)
                                    Toast.makeText(
                                        context,
                                        "Register is successful.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }.addOnFailureListener { exception ->
                                    Toast.makeText(context, exception.message, Toast.LENGTH_SHORT)
                                        .show()
                                }
                        }
                    }.addOnFailureListener {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}