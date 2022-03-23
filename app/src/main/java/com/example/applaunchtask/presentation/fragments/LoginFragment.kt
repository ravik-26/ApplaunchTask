package com.example.applaunchtask.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.applaunchtask.R
import com.example.applaunchtask.databinding.FragmentLoginBinding
import com.example.applaunchtask.databinding.FragmentOnboardingBinding
import com.example.applaunchtask.presentation.MainViewModel
import com.example.applaunchtask.util.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!! // Helper Property

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            login.setOnClickListener {
                val username = username.text.toString()
                val password = password.text.toString()

                Log.d("TAG", "onViewCreated: $username and $password")
                val loginResult = viewModel.verifyLogin(username, password)

                if (loginResult) {
                    sessionManager.createSession()
                    findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
                } else{
                    Toast.makeText(context, "login failed .. invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}