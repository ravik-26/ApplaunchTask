package com.example.applaunchtask.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.applaunchtask.R
import com.example.applaunchtask.databinding.FragmentOnboardingBinding
import com.example.applaunchtask.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!! // Helper Property

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val loggedInUser = sessionManager.isUserLoggedIn()

        Log.d("TAG", "onViewCreated: shared pref value $loggedInUser")

        if(loggedInUser) {
            navController.navigate(R.id.action_onboardingFragment_to_userListFragment)
        } else {
            binding.loginButton.setOnClickListener {
                navController.navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}