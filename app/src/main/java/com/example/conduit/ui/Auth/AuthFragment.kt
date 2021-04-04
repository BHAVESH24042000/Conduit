package com.example.conduit.ui.Auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.conduit.R
import com.example.conduit.databinding.FragmentAuthBinding
import com.google.android.material.tabs.TabLayout

//https://developer.android.com/guide/navigation/navigation-getting-started
class AuthFragment: Fragment() {

    private var _binding:FragmentAuthBinding?=null
    private var navController: NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        _binding= FragmentAuthBinding.inflate(inflater, container, false)

        navController = _binding?.let {
            Navigation.findNavController(it.root.findViewById(R.id.authFragmentNavHost))
        }
        _binding?.authTabLayout?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        navController?.navigate(R.id.gotoLoginFragment)
                    }
                    1 -> {
                        navController?.navigate(R.id.gotoSignupFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}