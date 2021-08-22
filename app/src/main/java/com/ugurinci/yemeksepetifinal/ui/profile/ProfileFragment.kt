package com.ugurinci.yemeksepetifinal.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ugurinci.yemeksepetifinal.data.remote.User
import com.ugurinci.yemeksepetifinal.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private lateinit var db: FirebaseFirestore

    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        binding.apply {
            db.collection("Users").document(auth.uid!!).get().addOnSuccessListener {
                user = it.toObject()!!
                cardViewProfile.visibility = View.VISIBLE
                textViewProfileName.text = user.name
                textViewProfileEmail.text = user.email
                textViewProfilePhone.text = user.phone
                textViewProfileAddress.text = user.address
                cardViewProfile.visibility = View.VISIBLE
                progressBarProfile.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}