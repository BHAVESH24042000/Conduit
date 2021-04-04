package com.example.conduit.ui.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.conduit.data.UserRepo.login
//import com.example.conduit.databinding.FragmentLoginBinding
import com.example.conduit.databinding.FragmentLoginSignupBinding
import com.example.conduit.ui.feed.FeedViewModel

class LoginFragment: Fragment() {

    private var _binding: FragmentLoginSignupBinding?=null
    val authViewModel: AuthViewModel by activityViewModels() //https://developer.android.com/topic/libraries/architecture/viewmodel
// this model is linked with main activity, read share data between fragments in doc

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding= FragmentLoginSignupBinding.inflate(inflater, container, false)
        _binding?.usernameEditText?.isVisible=false
        //authViewModel= ViewModelProvider(this).get(AuthViewModel::class.java)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            submitButton.setOnClickListener {
                authViewModel.login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }


        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}